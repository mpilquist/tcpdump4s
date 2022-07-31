package tcpdump4s

import libpcap.*
import libpcap.types.*
import libpcap.functions.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.posix.sys.socket.{AF_INET, AF_INET6}

object Pcap:
  case class Interface(name: String, description: String, addresses: List[SockAddr])
  case class SockAddr(s: String)
//   case class SockAddr(addr, netmask, broadaddr, dstaddr)

  private def makeErrorBuffer(using Zone) = stackalloc[Byte](256)
  private def fromNullableString(cstr: CString): String =
    if cstr eq null then "" else fromCString(cstr)

  private def getIpString(ptrSa: Ptr[sockaddr]): Option[String] =
    val host = stackalloc[Byte](256)
    val serv = stackalloc[Byte](256)
    val rc = getnameinfo(ptrSa, (!ptrSa).len, host, 256, serv, 256, NI_NUMERICHOST)
    if rc == 0 then Some(fromNullableString(host)) else None

  private def fromPcapIf(ptr: Ptr[pcap_if]): List[Interface] = ???
  private def fromPcapAddr(ptr: Ptr[pcap_addr]): List[SockAddr] =
    val ab = List.newBuilder[SockAddr]
    var addr = ptr
    while addr ne null do
      val ptrSa: Ptr[sockaddr] = (!addr).addr
      val family = (!ptrSa).family.toInt
      if family == AF_INET || family == AF_INET6
      then getIpString(ptrSa).foreach(s => ab += SockAddr(s))
      addr = (!addr).next
    ab.result()

  def interfaces: List[Interface] =
    Zone { implicit z =>
      val p = stackalloc[Ptr[pcap_if]](1)
      val errbuf = makeErrorBuffer
      val rc = pcap_findalldevs(p, errbuf)
      if rc == 0 then
        val bldr = List.newBuilder[Interface]
        var q = !p
        while q ne null do
          val entry = !q
          bldr += Interface(fromCString(entry.name), fromNullableString(entry.description), fromPcapAddr(entry.addresses))
          q = entry.next
        bldr.result()
      else throw new RuntimeException(s"pcap_findalldevs failed with error code $rc: ${fromCString(errbuf)}")
    }