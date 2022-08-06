package tcpdump4s

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scalanative.libc.*
import scalanative.posix.sys.time.timeval

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

    opaque type pcap = CStruct0
    object pcap:
      given _tag: Tag[pcap] = Tag.materializeCStruct0Tag

    opaque type pcap_pkthdr = CStruct3[timeval, CUnsignedInt, CUnsignedInt]
    object pcap_pkthdr:
      given _tag: Tag[pcap_pkthdr] = Tag.materializeCStruct3Tag[timeval, CUnsignedInt, CUnsignedInt]
      extension (struct: pcap_pkthdr)
        def ts: timeval = struct._1
        def caplen: CUnsignedInt = struct._2
        def len: CUnsignedInt = struct._3

    opaque type bpf_insn = CStruct4[CUnsignedShort, CUnsignedChar, CUnsignedChar, CUnsignedInt]
    object bpf_insn:
      given _tag: Tag[bpf_insn] = Tag.materializeCStruct4Tag[CUnsignedShort, CUnsignedChar, CUnsignedChar, CUnsignedInt]
      extension (struct: bpf_insn)
        def code: CUnsignedShort = struct._1
        def jt: CUnsignedChar = struct._2
        def jf: CUnsignedChar = struct._3
        def k: CUnsignedInt = struct._4

    opaque type bpf_program = CStruct2[CUnsignedInt, Ptr[bpf_insn]]
    object bpf_program:
      given _tag: Tag[bpf_program] = Tag.materializeCStruct2Tag[CUnsignedInt, Ptr[bpf_insn]]

  val NI_NUMERICHOST: CInt = 2

  @link("pcap")
  @extern
  object functions:
    import types.*
    def pcap_findalldevs(interfaces: Ptr[Ptr[pcap_if]], errbuf: CString): CInt = extern
    def pcap_freealldevs(interfaces: Ptr[pcap_if]): Unit = extern
    def pcap_open_live(device: CString, snaplen: CInt, promisc: CInt, to_ms: CInt, errbuf: CString): Ptr[pcap] = extern
    def pcap_compile(p: Ptr[pcap], fp: Ptr[bpf_program], str: CString, optimize: CInt, netmask: CUnsignedInt): CInt = extern
    def pcap_setfilter(p: Ptr[pcap], fp: Ptr[bpf_program]): CInt = extern
    def pcap_next_ex(p: Ptr[pcap], pkt_header: Ptr[Ptr[pcap_pkthdr]], pkt_data: Ptr[Ptr[Byte]]): Int = extern
    def pcap_close(p: Ptr[pcap]): Unit = extern
    def pcap_datalink(p: Ptr[pcap]): CInt = extern

    // TODO: built-in getnameinfo not working on OS X, might need sn 0.5 with socket fixes
    def getnameinfo(sa: Ptr[sockaddr], salen: CSize, host: CString, hostlen: CInt, serv: CString, servlen: CInt, flags: CInt): CInt = extern
