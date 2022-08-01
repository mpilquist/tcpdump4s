package tcpdump4s

import libpcap.*
import libpcap.types.*
import libpcap.functions.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.posix.sys.socket.{AF_INET, AF_INET6}

import com.comcast.ip4s.IpAddress

object Pcap:
  case class Interface(name: String, description: String, addresses: List[SockAddr])
  case class SockAddr(address: IpAddress)
//   case class SockAddr(addr, netmask, broadaddr, dstaddr)

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
    getIpString(ptrSa).map(s => IpAddress.fromString(s.takeWhile(_ != '%')).get)

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
      then getIpAddress(ptrSa).foreach(s => bldr += SockAddr(s))
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