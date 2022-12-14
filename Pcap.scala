package tcpdump4s

import libpcap.*
import libpcap.types.*
import libpcap.functions.*
import scala.concurrent.duration.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.posix.sys.socket.{AF_INET, AF_INET6}
import scalanative.posix.sys.timeOps.timevalValOps

import cats.effect.{IO, Resource}
import cats.syntax.all.*
import com.comcast.ip4s.{Cidr, IpAddress}
import fs2.Stream
import fs2.protocols.pcap.LinkType
import fs2.timeseries.TimeStamped
import scodec.bits.ByteVector

object Pcap:
  case class Interface(name: String, description: String, addresses: List[Address], flags: InterfaceFlags)
  case class Address(
    address: IpAddress,
    netmask: Option[IpAddress],
    broadcastAddress: Option[IpAddress],
    destinationAddress: Option[IpAddress]
  ):
    private def prefixCount: Int =
      netmask
        .map(_.toBytes.foldLeft(0)((acc, b) => acc + java.lang.Integer.bitCount(0xff & b)))
        .getOrElse(address.toBytes.size * 8)

    // TODO ip4s should have a way to create a cidr from an address and netmask
    def cidr: Cidr[IpAddress] = Cidr(address, prefixCount)

  opaque type InterfaceFlags = CUnsignedInt
  extension (self: InterfaceFlags)
    def isLoopback: Boolean = (self.toInt & 1) > 0
    def isUp: Boolean = (self.toInt & 2) > 0
    def isRunning: Boolean = (self.toInt & 4) > 0
    def isWireless: Boolean = (self.toInt & 8) > 0

  private def zone[A](f: Zone ?=> A): A = Zone(z => f(using z))

  private def makeErrorBuffer(using Zone) = alloc[CChar](256)
  private def fromNullableString(cstr: CString): String =
    if cstr eq null then "" else fromCString(cstr)

  private def getIpString(ptrSa: Ptr[sockaddr]): Option[String] =
    val size = 256
    val host = stackalloc[Byte](size)
    val serv = stackalloc[Byte](size)
    val rc = getnameinfo(ptrSa, (!ptrSa).len, host, size, serv, size, NI_NUMERICHOST)
    if rc == 0 then Some(fromNullableString(host)) else None

  private def getIpAddress(ptrSa: Ptr[sockaddr]): Option[IpAddress] =
    if ptrSa eq null then None
    else getIpString(ptrSa).map(s => IpAddress.fromString(s.takeWhile(_ != '%')).get)

  private def getOptionalIpAddress(ptrSa: Ptr[sockaddr]): Option[Option[IpAddress]] =
    if ptrSa eq null then Some(None) else getIpAddress(ptrSa).map(Some(_))

  private def fromPcapIf(ptr: Ptr[pcap_if]): List[Interface] =
    val bldr = List.newBuilder[Interface]
    var ptrPcapIf = ptr
    while ptrPcapIf ne null do
      val entry = !ptrPcapIf
      bldr += Interface(
        fromCString(entry.name),
        fromNullableString(entry.description),
        fromPcapAddr(entry.addresses),
        entry.flags)
      ptrPcapIf = entry.next
    bldr.result()

  private def fromPcapAddr(ptr: Ptr[pcap_addr]): List[Address] =
    val bldr = List.newBuilder[Address]
    var addr = ptr
    while addr ne null do
      val ptrSa: Ptr[sockaddr] = (!addr).addr
      val family = (!ptrSa).family.toInt
      if family == AF_INET || family == AF_INET6
      then
        (getIpAddress(ptrSa),
          getOptionalIpAddress((!addr).netmask),
          getOptionalIpAddress((!addr).broadaddr),
          getOptionalIpAddress((!addr).dstaddr)
        ).mapN(Address.apply).foreach(bldr += _)
      addr = (!addr).next
    bldr.result()

  def interfaces: IO[List[Interface]] = IO {
    zone {
      val p = alloc[Ptr[pcap_if]]()
      val errbuf = makeErrorBuffer
      val rc = pcap_findalldevs(p, errbuf)
      try
        if rc == 0 then fromPcapIf(!p)
        else throw new RuntimeException(s"pcap_findalldevs failed with error code $rc: ${fromCString(errbuf)}")
      finally
        pcap_freealldevs(!p)
    }
  }

  def openLive(device: String, promiscuousMode: Boolean): Resource[IO, Pcap] =
    Resource.make(IO {
      zone {
        val errbuf = makeErrorBuffer
        val p = pcap_open_live(toCString(device), 65535, if promiscuousMode then 1 else 0, 100, errbuf)
        if p eq null then throw new RuntimeException(s"pcap_open_live failed with error: ${fromCString(errbuf)}")
        else new Pcap(p)
      }
    })(_.close)

  def livePackets(
    device: String,
    promiscuousMode: Boolean,
    filter: Option[String]
  ): Resource[IO, (LinkType, Stream[IO, TimeStamped[ByteVector]])] =
    Pcap.openLive(device, promiscuousMode)
      .evalTap(p => filter.map(fltr => p.setFilter(fltr)).getOrElse(IO.unit))
      .map(p => LinkType.fromLong(p.linkType) -> Stream.repeatEval(p.next))

class Pcap private (p: Ptr[pcap]):
  import Pcap.*

  val linkType: Int = pcap_datalink(p)

  def setLinkType(dlt: Int): IO[Unit] = IO {
    val rc = pcap_set_datalink(p, dlt)
    if rc != 0 then
      val err = pcap_geterr(p)
      throw new RuntimeException("setLinkType failed: " + fromCString(err))
  }

  def setFilter(s: String): IO[Unit] = IO {
    zone {
      val fp = alloc[bpf_program]()
      var rc = pcap_compile(p, fp, toCString(s), 1, -1.toUInt)
      if rc != 0 then throw new RuntimeException(s"pcap_compile failed with error code: $rc")
      rc = pcap_setfilter(p, fp)
      if rc != 0 then throw new RuntimeException(s"pcap_setfilter failed with error code: $rc")
    }
  }

  def next: IO[TimeStamped[ByteVector]] = IO {
    zone {
      val ppHdr = alloc[Ptr[pcap_pkthdr]]()
      val ppData = alloc[Ptr[Byte]]()
      var rc = 0
      while rc == 0 do rc = pcap_next_ex(p, ppHdr, ppData)
      if rc != 1 then throw new RuntimeException(s"pcap_next_ex failed with error code: $rc")
      val pHdr = !ppHdr
      val ts = (!pHdr).ts.tv_sec.toInt.seconds + (!pHdr).ts.tv_usec.toInt.microseconds
      val size = (!pHdr).caplen.toInt
      TimeStamped(ts, ByteVector.fromPtr(!ppData, size))
    }
  }

  private def close: IO[Unit] =
    IO(pcap_close(p))

