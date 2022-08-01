package tcpdump4s

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scalanative.libc.*

object libpcap:

  object types:
    opaque type sockaddr = CStruct2[UByte, UByte]
    object sockaddr:
      given _tag: Tag[sockaddr] = Tag.materializeCStruct2Tag[UByte, UByte]
      extension (struct: sockaddr)
        def len: UByte = struct._1
        def family: UByte = struct._2

    opaque type sockaddr_in = CStruct5[UByte, UByte, UShort, UInt, CArray[CChar, Nat._8]]
    object sockaddr_in:
      given _tag: Tag[sockaddr_in] = Tag.materializeCStruct5Tag[UByte, UByte, UShort, UInt, CArray[CChar, Nat._8]]
      extension (struct: sockaddr_in)
        def sin_len: UByte = struct._1
        def sin_family: UByte = struct._2
        def sin_port: UShort = struct._3
        def sin_addr: UInt = struct._4

    opaque type sockaddr_in6 = CStruct6[UByte, UByte, UShort, UInt, CArray[Byte, Nat.Digit2[Nat._1, Nat._6]], UInt]
    object sockaddr_in6:
      given _tag: Tag[sockaddr_in6] = Tag.materializeCStruct6Tag[UByte, UByte, UShort, UInt, CArray[Byte, Nat.Digit2[Nat._1, Nat._6]], UInt]
      extension (struct: sockaddr_in6)
        def sin6_len: UByte = struct._1
        def sen6_family: UByte = struct._2

    opaque type pcap_addr = CStruct5[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
    object pcap_addr:
      given _tag: Tag[pcap_addr] = Tag.materializeCStruct5Tag[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
      extension (struct: pcap_addr)
        def next: Ptr[pcap_addr] = struct._1.asInstanceOf[Ptr[pcap_addr]]
        def addr: Ptr[sockaddr] = struct._2
        def netmask: Ptr[sockaddr] = struct._3
        def broadaddr: Ptr[sockaddr] = struct._4
        def dstaddr: Ptr[sockaddr] = struct._5

    opaque type pcap_if = CStruct5[Ptr[Byte], CString, CString, Ptr[Byte], CUnsignedInt]
    object pcap_if:
      given _tag: Tag[pcap_if] = Tag.materializeCStruct5Tag[Ptr[Byte], CString, CString, Ptr[Byte], CUnsignedInt]
      extension (struct: pcap_if)
        def next: Ptr[pcap_if] = struct._1.asInstanceOf[Ptr[pcap_if]]
        def name: CString = struct._2
        def description: CString = struct._3
        def addresses: Ptr[pcap_addr] = struct._4.asInstanceOf[Ptr[pcap_addr]]
        def flags: CUnsignedInt = struct._5

  val NI_NUMERICHOST: CInt = 2

  @link("pcap")
  @extern
  object functions:
    import types.*
    def pcap_findalldevs(interfaces: Ptr[Ptr[pcap_if]], errbuf: CString): CInt = extern

    // TODO: getnameinfo not working on OS X, might need sn 0.5 with socket fixes
    def getnameinfo(sa: Ptr[sockaddr], salen: CSize, host: CString, hostlen: CInt, serv: CString, servlen: CInt, flags: CInt): CInt = extern
