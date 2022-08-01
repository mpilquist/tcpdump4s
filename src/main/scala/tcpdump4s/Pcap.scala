package tcpdump4s

import libpcap.*
import libpcap.types.*
import libpcap.functions.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.posix.sys.socket.{AF_INET, AF_INET6}

import cats.syntax.all.*
import com.comcast.ip4s.{Cidr, IpAddress}

object Pcap:
  case class Interface(name: String, description: String, addresses: List[SockAddr])
  case class SockAddr(address: IpAddress, netmask: IpAddress, broadcastAddress: Option[IpAddress], destinationAddress: Option[IpAddress]):
    private def prefixCount: Int = netmask.toBytes.foldLeft(0)((acc, b) => acc + java.lang.Integer.bitCount(0xff & b))
    // TODO ip4s should have a way to create a cidr from an address and netmask
    def cidr: Cidr[IpAddress] = Cidr(address, prefixCount)

  private def zone[A](f: Zone ?=> A): A = Zone(z => f(using z))

  private def makeErrorBuffer(using Zone) = alloc[Byte](256)
  private def fromNullableString(cstr: CString): String =
    if cstr eq null then "" else fromCString(cstr)

  private def getIpString(ptrSa: Ptr[sockaddr]): Option[String] =
    val host = stackalloc[Byte](256)
    val serv = stackalloc[Byte](256)
    val rc = getnameinfo(ptrSa, (!ptrSa).len, host, 256, serv, 256, NI_NUMERICHOST)
    if rc == 0 then Some(fromNullableString(host)) else None

  private def getIpAddress(ptrSa: Ptr[sockaddr]): Option[IpAddress] =
    if ptrSa eq null then None
    else getIpString(ptrSa).map(s => IpAddress.fromString(s.takeWhile(_ != '%')).get)

  private def fromPcapIf(ptr: Ptr[pcap_if]): List[Interface] =
    val bldr = List.newBuilder[Interface]
    var ptrPcapIf = ptr
    while ptrPcapIf ne null do
      val entry = !ptrPcapIf
      bldr += Interface(fromCString(entry.name), fromNullableString(entry.description), fromPcapAddr(entry.addresses))
      ptrPcapIf = entry.next
    bldr.result()

  private def fromPcapAddr(ptr: Ptr[pcap_addr]): List[SockAddr] =
    val bldr = List.newBuilder[SockAddr]
    var addr = ptr
    while addr ne null do
      val ptrSa: Ptr[sockaddr] = (!addr).addr
      val family = (!ptrSa).family.toInt
      if family == AF_INET || family == AF_INET6
      then
        (getIpAddress(ptrSa),
          getIpAddress((!addr).netmask),
          if (!addr).broadaddr eq null then Some(None) else getIpAddress((!addr).broadaddr).map(Some(_)),
          if (!addr).dstaddr eq null then Some(None) else getIpAddress((!addr).dstaddr).map(Some(_)),
        ).mapN(SockAddr.apply).foreach(bldr += _)
      addr = (!addr).next
    bldr.result()

  def interfaces: List[Interface] =
    zone {
      val p = alloc[Ptr[pcap_if]]()
      val errbuf = makeErrorBuffer
      val rc = pcap_findalldevs(p, errbuf)
      if rc == 0 then fromPcapIf(!p)
      else throw new RuntimeException(s"pcap_findalldevs failed with error code $rc: ${fromCString(errbuf)}")
    }