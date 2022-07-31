package libpcap

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scalanative.libc.*

object predef:
  
  trait CEnumU[T](using eq: T =:= UInt):
    given Tag[T] = Tag.UInt.asInstanceOf[Tag[T]]
    extension (t: T)
     def int: CInt = eq.apply(t).toInt
     def uint: CUnsignedInt = eq.apply(t)
        
object types:
  import predef.*
  opaque type clockid_t = CUnsignedInt
  object clockid_t extends CEnumU[clockid_t]:
    given _tag: Tag[clockid_t] = Tag.UInt
    inline def define(inline a: Long): clockid_t = a.toUInt
    val _CLOCK_REALTIME = define(0)
    val _CLOCK_MONOTONIC = define(6)
    val _CLOCK_MONOTONIC_RAW = define(4)
    val _CLOCK_MONOTONIC_RAW_APPROX = define(5)
    val _CLOCK_UPTIME_RAW = define(8)
    val _CLOCK_UPTIME_RAW_APPROX = define(9)
    val _CLOCK_PROCESS_CPUTIME_ID = define(12)
    val _CLOCK_THREAD_CPUTIME_ID = define(16)
    extension (a: clockid_t)
      inline def &(b: clockid_t): clockid_t = a & b
      inline def |(b: clockid_t): clockid_t = a | b
      inline def is(b: clockid_t): Boolean = (a & b) == b

  opaque type pcap_direction_t = CUnsignedInt
  object pcap_direction_t extends CEnumU[pcap_direction_t]:
    given _tag: Tag[pcap_direction_t] = Tag.UInt
    inline def define(inline a: Long): pcap_direction_t = a.toUInt
    val PCAP_D_INOUT = define(0)
    val PCAP_D_IN = define(1)
    val PCAP_D_OUT = define(2)
    extension (a: pcap_direction_t)
      inline def &(b: pcap_direction_t): pcap_direction_t = a & b
      inline def |(b: pcap_direction_t): pcap_direction_t = a | b
      inline def is(b: pcap_direction_t): Boolean = (a & b) == b
  type FILE = scala.scalanative.libc.stdio.FILE
  object FILE: 
    val _tag: Tag[FILE] = summon[Tag[scala.scalanative.libc.stdio.FILE]]

  type __darwin_blkcnt_t = __int64_t
  object __darwin_blkcnt_t: 
    given _tag: Tag[__darwin_blkcnt_t] = __int64_t._tag

  type __darwin_blksize_t = __int32_t
  object __darwin_blksize_t: 
    given _tag: Tag[__darwin_blksize_t] = __int32_t._tag

  opaque type __darwin_clock_t = CUnsignedLongInt
  object __darwin_clock_t: 
    given _tag: Tag[__darwin_clock_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): __darwin_clock_t = o

  type __darwin_dev_t = __int32_t
  object __darwin_dev_t: 
    given _tag: Tag[__darwin_dev_t] = __int32_t._tag

  opaque type __darwin_fsblkcnt_t = CUnsignedInt
  object __darwin_fsblkcnt_t: 
    given _tag: Tag[__darwin_fsblkcnt_t] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): __darwin_fsblkcnt_t = o

  opaque type __darwin_fsfilcnt_t = CUnsignedInt
  object __darwin_fsfilcnt_t: 
    given _tag: Tag[__darwin_fsfilcnt_t] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): __darwin_fsfilcnt_t = o

  type __darwin_gid_t = __uint32_t
  object __darwin_gid_t: 
    given _tag: Tag[__darwin_gid_t] = __uint32_t._tag

  type __darwin_id_t = __uint32_t
  object __darwin_id_t: 
    given _tag: Tag[__darwin_id_t] = __uint32_t._tag

  type __darwin_ino64_t = __uint64_t
  object __darwin_ino64_t: 
    given _tag: Tag[__darwin_ino64_t] = __uint64_t._tag

  type __darwin_ino_t = __darwin_ino64_t
  object __darwin_ino_t: 
    given _tag: Tag[__darwin_ino_t] = __darwin_ino64_t._tag

  type __darwin_mode_t = __uint16_t
  object __darwin_mode_t: 
    given _tag: Tag[__darwin_mode_t] = __uint16_t._tag

  type __darwin_off_t = __int64_t
  object __darwin_off_t: 
    given _tag: Tag[__darwin_off_t] = __int64_t._tag

  type __darwin_pid_t = __int32_t
  object __darwin_pid_t: 
    given _tag: Tag[__darwin_pid_t] = __int32_t._tag

  opaque type __darwin_pthread_key_t = CUnsignedLongInt
  object __darwin_pthread_key_t: 
    given _tag: Tag[__darwin_pthread_key_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): __darwin_pthread_key_t = o

  opaque type __darwin_pthread_t = Ptr[_opaque_pthread_t]
  object __darwin_pthread_t: 
    given _tag: Tag[__darwin_pthread_t] = Tag.Ptr[_opaque_pthread_t](_opaque_pthread_t._tag)
    inline def apply(inline o: Ptr[_opaque_pthread_t]): __darwin_pthread_t = o

  opaque type __darwin_size_t = CUnsignedLongInt
  object __darwin_size_t: 
    given _tag: Tag[__darwin_size_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): __darwin_size_t = o

  type __darwin_socklen_t = __uint32_t
  object __darwin_socklen_t: 
    given _tag: Tag[__darwin_socklen_t] = __uint32_t._tag

  type __darwin_suseconds_t = __int32_t
  object __darwin_suseconds_t: 
    given _tag: Tag[__darwin_suseconds_t] = __int32_t._tag

  opaque type __darwin_time_t = CLongInt
  object __darwin_time_t: 
    given _tag: Tag[__darwin_time_t] = Tag.Long
    inline def apply(inline o: CLongInt): __darwin_time_t = o

  type __darwin_uid_t = __uint32_t
  object __darwin_uid_t: 
    given _tag: Tag[__darwin_uid_t] = __uint32_t._tag

  type __darwin_useconds_t = __uint32_t
  object __darwin_useconds_t: 
    given _tag: Tag[__darwin_useconds_t] = __uint32_t._tag

  opaque type __int32_t = CInt
  object __int32_t: 
    given _tag: Tag[__int32_t] = Tag.Int
    inline def apply(inline o: CInt): __int32_t = o

  opaque type __int64_t = CLongLong
  object __int64_t: 
    given _tag: Tag[__int64_t] = Tag.Long
    inline def apply(inline o: CLongLong): __int64_t = o

  opaque type __uint16_t = CUnsignedShort
  object __uint16_t: 
    given _tag: Tag[__uint16_t] = Tag.UShort
    inline def apply(inline o: CUnsignedShort): __uint16_t = o

  opaque type __uint32_t = CUnsignedInt
  object __uint32_t: 
    given _tag: Tag[__uint32_t] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): __uint32_t = o

  opaque type __uint64_t = CUnsignedLongLong
  object __uint64_t: 
    given _tag: Tag[__uint64_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongLong): __uint64_t = o

  opaque type __uint8_t = CUnsignedChar
  object __uint8_t: 
    given _tag: Tag[__uint8_t] = Tag.UByte
    inline def apply(inline o: CUnsignedChar): __uint8_t = o

  type blkcnt_t = __darwin_blkcnt_t
  object blkcnt_t: 
    given _tag: Tag[blkcnt_t] = __darwin_blkcnt_t._tag

  type blksize_t = __darwin_blksize_t
  object blksize_t: 
    given _tag: Tag[blksize_t] = __darwin_blksize_t._tag

  opaque type bpf_int32 = CInt
  object bpf_int32: 
    given _tag: Tag[bpf_int32] = Tag.Int
    inline def apply(inline o: CInt): bpf_int32 = o

  type bpf_u_int32 = u_int
  object bpf_u_int32: 
    given _tag: Tag[bpf_u_int32] = u_int._tag

  opaque type caddr_t = CString
  object caddr_t: 
    given _tag: Tag[caddr_t] = Tag.Ptr[CChar](Tag.Byte)
    inline def apply(inline o: CString): caddr_t = o

  type clock_t = __darwin_clock_t
  object clock_t: 
    given _tag: Tag[clock_t] = __darwin_clock_t._tag

  type daddr_t = int32_t
  object daddr_t: 
    given _tag: Tag[daddr_t] = int32_t._tag

  type dev_t = __darwin_dev_t
  object dev_t: 
    given _tag: Tag[dev_t] = __darwin_dev_t._tag

  opaque type errno_t = CInt
  object errno_t: 
    given _tag: Tag[errno_t] = Tag.Int
    inline def apply(inline o: CInt): errno_t = o

  type fd_mask = __int32_t
  object fd_mask: 
    given _tag: Tag[fd_mask] = __int32_t._tag

  type fixpt_t = u_int32_t
  object fixpt_t: 
    given _tag: Tag[fixpt_t] = u_int32_t._tag

  type fsblkcnt_t = __darwin_fsblkcnt_t
  object fsblkcnt_t: 
    given _tag: Tag[fsblkcnt_t] = __darwin_fsblkcnt_t._tag

  type fsfilcnt_t = __darwin_fsfilcnt_t
  object fsfilcnt_t: 
    given _tag: Tag[fsfilcnt_t] = __darwin_fsfilcnt_t._tag

  type gid_t = __darwin_gid_t
  object gid_t: 
    given _tag: Tag[gid_t] = __darwin_gid_t._tag

  type id_t = __darwin_id_t
  object id_t: 
    given _tag: Tag[id_t] = __darwin_id_t._tag

  type in_addr_t = __uint32_t
  object in_addr_t: 
    given _tag: Tag[in_addr_t] = __uint32_t._tag

  type in_port_t = __uint16_t
  object in_port_t: 
    given _tag: Tag[in_port_t] = __uint16_t._tag

  type ino64_t = __darwin_ino64_t
  object ino64_t: 
    given _tag: Tag[ino64_t] = __darwin_ino64_t._tag

  type ino_t = __darwin_ino_t
  object ino_t: 
    given _tag: Tag[ino_t] = __darwin_ino_t._tag

  type int32_t = scala.scalanative.unsafe.CInt
  object int32_t: 
    val _tag: Tag[int32_t] = summon[Tag[scala.scalanative.unsafe.CInt]]

  type int64_t = scala.Long
  object int64_t: 
    val _tag: Tag[int64_t] = summon[Tag[scala.Long]]

  type key_t = __int32_t
  object key_t: 
    given _tag: Tag[key_t] = __int32_t._tag

  type mode_t = __darwin_mode_t
  object mode_t: 
    given _tag: Tag[mode_t] = __darwin_mode_t._tag

  type nlink_t = __uint16_t
  object nlink_t: 
    given _tag: Tag[nlink_t] = __uint16_t._tag

  type off_t = __darwin_off_t
  object off_t: 
    given _tag: Tag[off_t] = __darwin_off_t._tag

  opaque type pcap_handler = CFuncPtr3[Ptr[u_char], Ptr[pcap_pkthdr], Ptr[u_char], Unit]
  object pcap_handler: 
    given _tag: Tag[pcap_handler] = Tag.materializeCFuncPtr3[Ptr[u_char], Ptr[pcap_pkthdr], Ptr[u_char], Unit]
    inline def apply(inline o: CFuncPtr3[Ptr[u_char], Ptr[pcap_pkthdr], Ptr[u_char], Unit]): pcap_handler = o

  type pid_t = __darwin_pid_t
  object pid_t: 
    given _tag: Tag[pid_t] = __darwin_pid_t._tag

  type pthread_attr_t = __darwin_pthread_attr_t
  object pthread_attr_t: 
    given _tag: Tag[pthread_attr_t] = __darwin_pthread_attr_t._tag

  type pthread_cond_t = __darwin_pthread_cond_t
  object pthread_cond_t: 
    given _tag: Tag[pthread_cond_t] = __darwin_pthread_cond_t._tag

  type pthread_condattr_t = __darwin_pthread_condattr_t
  object pthread_condattr_t: 
    given _tag: Tag[pthread_condattr_t] = __darwin_pthread_condattr_t._tag

  type pthread_key_t = __darwin_pthread_key_t
  object pthread_key_t: 
    given _tag: Tag[pthread_key_t] = __darwin_pthread_key_t._tag

  type pthread_mutex_t = __darwin_pthread_mutex_t
  object pthread_mutex_t: 
    given _tag: Tag[pthread_mutex_t] = __darwin_pthread_mutex_t._tag

  type pthread_mutexattr_t = __darwin_pthread_mutexattr_t
  object pthread_mutexattr_t: 
    given _tag: Tag[pthread_mutexattr_t] = __darwin_pthread_mutexattr_t._tag

  type pthread_once_t = __darwin_pthread_once_t
  object pthread_once_t: 
    given _tag: Tag[pthread_once_t] = __darwin_pthread_once_t._tag

  type pthread_rwlock_t = __darwin_pthread_rwlock_t
  object pthread_rwlock_t: 
    given _tag: Tag[pthread_rwlock_t] = __darwin_pthread_rwlock_t._tag

  type pthread_rwlockattr_t = __darwin_pthread_rwlockattr_t
  object pthread_rwlockattr_t: 
    given _tag: Tag[pthread_rwlockattr_t] = __darwin_pthread_rwlockattr_t._tag

  type pthread_t = __darwin_pthread_t
  object pthread_t: 
    given _tag: Tag[pthread_t] = __darwin_pthread_t._tag

  opaque type qaddr_t = Ptr[quad_t]
  object qaddr_t: 
    given _tag: Tag[qaddr_t] = Tag.Ptr[quad_t](quad_t._tag)
    inline def apply(inline o: Ptr[quad_t]): qaddr_t = o

  type quad_t = int64_t
  object quad_t: 
    given _tag: Tag[quad_t] = int64_t._tag

  type rsize_t = __darwin_size_t
  object rsize_t: 
    given _tag: Tag[rsize_t] = __darwin_size_t._tag

  type sa_family_t = __uint8_t
  object sa_family_t: 
    given _tag: Tag[sa_family_t] = __uint8_t._tag

  type sae_associd_t = __uint32_t
  object sae_associd_t: 
    given _tag: Tag[sae_associd_t] = __uint32_t._tag

  type sae_connid_t = __uint32_t
  object sae_connid_t: 
    given _tag: Tag[sae_connid_t] = __uint32_t._tag

  type segsz_t = int32_t
  object segsz_t: 
    given _tag: Tag[segsz_t] = int32_t._tag

  type size_t = scala.scalanative.unsafe.CSize
  object size_t: 
    val _tag: Tag[size_t] = summon[Tag[scala.scalanative.unsafe.CSize]]

  type socklen_t = __darwin_socklen_t
  object socklen_t: 
    given _tag: Tag[socklen_t] = __darwin_socklen_t._tag

  type ssize_t = scala.scalanative.unsafe.CSSize
  object ssize_t: 
    val _tag: Tag[ssize_t] = summon[Tag[scala.scalanative.unsafe.CSSize]]

  type suseconds_t = __darwin_suseconds_t
  object suseconds_t: 
    given _tag: Tag[suseconds_t] = __darwin_suseconds_t._tag

  type swblk_t = int32_t
  object swblk_t: 
    given _tag: Tag[swblk_t] = int32_t._tag

  type time_t = scala.scalanative.posix.time.time_t
  object time_t: 
    val _tag: Tag[time_t] = summon[Tag[scala.scalanative.posix.time.time_t]]

  opaque type u_char = CUnsignedChar
  object u_char: 
    given _tag: Tag[u_char] = Tag.UByte
    inline def apply(inline o: CUnsignedChar): u_char = o

  opaque type u_int = CUnsignedInt
  object u_int: 
    given _tag: Tag[u_int] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): u_int = o

  opaque type u_int32_t = CUnsignedInt
  object u_int32_t: 
    given _tag: Tag[u_int32_t] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): u_int32_t = o

  opaque type u_int64_t = CUnsignedLongLong
  object u_int64_t: 
    given _tag: Tag[u_int64_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongLong): u_int64_t = o

  opaque type u_long = CUnsignedLongInt
  object u_long: 
    given _tag: Tag[u_long] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): u_long = o

  type u_quad_t = u_int64_t
  object u_quad_t: 
    given _tag: Tag[u_quad_t] = u_int64_t._tag

  opaque type u_short = CUnsignedShort
  object u_short: 
    given _tag: Tag[u_short] = Tag.UShort
    inline def apply(inline o: CUnsignedShort): u_short = o

  type uid_t = __darwin_uid_t
  object uid_t: 
    given _tag: Tag[uid_t] = __darwin_uid_t._tag

  opaque type uint = CUnsignedInt
  object uint: 
    given _tag: Tag[uint] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): uint = o

  type uint32_t = scala.scalanative.unsigned.UInt
  object uint32_t: 
    val _tag: Tag[uint32_t] = summon[Tag[scala.scalanative.unsigned.UInt]]

  type useconds_t = __darwin_useconds_t
  object useconds_t: 
    given _tag: Tag[useconds_t] = __darwin_useconds_t._tag

  opaque type ushort = CUnsignedShort
  object ushort: 
    given _tag: Tag[ushort] = Tag.UShort
    inline def apply(inline o: CUnsignedShort): ushort = o
  opaque type __darwin_pthread_attr_t = CStruct2[CLongInt, CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]]
  object __darwin_pthread_attr_t:
    given _tag: Tag[__darwin_pthread_attr_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]]
    def apply()(using Zone): Ptr[__darwin_pthread_attr_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_attr_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat.Digit2[Nat._5, Nat._6]])(using Zone): Ptr[__darwin_pthread_attr_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_attr_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat.Digit2[Nat._5, Nat._6]] = struct._2
      def __opaque_=(value: CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_cond_t = CStruct2[CLongInt, CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]]
  object __darwin_pthread_cond_t:
    given _tag: Tag[__darwin_pthread_cond_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]]
    def apply()(using Zone): Ptr[__darwin_pthread_cond_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_cond_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat.Digit2[Nat._4, Nat._0]])(using Zone): Ptr[__darwin_pthread_cond_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_cond_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat.Digit2[Nat._4, Nat._0]] = struct._2
      def __opaque_=(value: CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_condattr_t = CStruct2[CLongInt, CArray[CChar, Nat._8]]
  object __darwin_pthread_condattr_t:
    given _tag: Tag[__darwin_pthread_condattr_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat._8]]
    def apply()(using Zone): Ptr[__darwin_pthread_condattr_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_condattr_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat._8])(using Zone): Ptr[__darwin_pthread_condattr_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_condattr_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat._8] = struct._2
      def __opaque_=(value: CArray[CChar, Nat._8]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_handler_rec = CStruct3[CFuncPtr1[Ptr[Byte], Unit], Ptr[Byte], Ptr[Byte]]
  object __darwin_pthread_handler_rec:
    given _tag: Tag[__darwin_pthread_handler_rec] = Tag.materializeCStruct3Tag[CFuncPtr1[Ptr[Byte], Unit], Ptr[Byte], Ptr[Byte]]
    def apply()(using Zone): Ptr[__darwin_pthread_handler_rec] = scala.scalanative.unsafe.alloc[__darwin_pthread_handler_rec](1)
    def apply(__routine : CFuncPtr1[Ptr[Byte], Unit], __arg : Ptr[Byte], __next : Ptr[__darwin_pthread_handler_rec])(using Zone): Ptr[__darwin_pthread_handler_rec] = 
      val ____ptr = apply()
      (!____ptr).__routine = __routine
      (!____ptr).__arg = __arg
      (!____ptr).__next = __next
      ____ptr
    extension (struct: __darwin_pthread_handler_rec)
      def __routine : CFuncPtr1[Ptr[Byte], Unit] = struct._1
      def __routine_=(value: CFuncPtr1[Ptr[Byte], Unit]): Unit = !struct.at1 = value
      def __arg : Ptr[Byte] = struct._2
      def __arg_=(value: Ptr[Byte]): Unit = !struct.at2 = value
      def __next : Ptr[__darwin_pthread_handler_rec] = struct._3.asInstanceOf[Ptr[__darwin_pthread_handler_rec]]
      def __next_=(value: Ptr[__darwin_pthread_handler_rec]): Unit = !struct.at3 = value.asInstanceOf[Ptr[Byte]]

  opaque type __darwin_pthread_mutex_t = CStruct2[CLongInt, CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]]
  object __darwin_pthread_mutex_t:
    given _tag: Tag[__darwin_pthread_mutex_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]]
    def apply()(using Zone): Ptr[__darwin_pthread_mutex_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_mutex_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat.Digit2[Nat._5, Nat._6]])(using Zone): Ptr[__darwin_pthread_mutex_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_mutex_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat.Digit2[Nat._5, Nat._6]] = struct._2
      def __opaque_=(value: CArray[CChar, Nat.Digit2[Nat._5, Nat._6]]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_mutexattr_t = CStruct2[CLongInt, CArray[CChar, Nat._8]]
  object __darwin_pthread_mutexattr_t:
    given _tag: Tag[__darwin_pthread_mutexattr_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat._8]]
    def apply()(using Zone): Ptr[__darwin_pthread_mutexattr_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_mutexattr_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat._8])(using Zone): Ptr[__darwin_pthread_mutexattr_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_mutexattr_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat._8] = struct._2
      def __opaque_=(value: CArray[CChar, Nat._8]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_once_t = CStruct2[CLongInt, CArray[CChar, Nat._8]]
  object __darwin_pthread_once_t:
    given _tag: Tag[__darwin_pthread_once_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat._8]]
    def apply()(using Zone): Ptr[__darwin_pthread_once_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_once_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat._8])(using Zone): Ptr[__darwin_pthread_once_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_once_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat._8] = struct._2
      def __opaque_=(value: CArray[CChar, Nat._8]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_rwlock_t = CStruct2[CLongInt, CArray[CChar, Nat.Digit3[Nat._1, Nat._9, Nat._2]]]
  object __darwin_pthread_rwlock_t:
    given _tag: Tag[__darwin_pthread_rwlock_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat.Digit3[Nat._1, Nat._9, Nat._2]]]
    def apply()(using Zone): Ptr[__darwin_pthread_rwlock_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_rwlock_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat.Digit3[Nat._1, Nat._9, Nat._2]])(using Zone): Ptr[__darwin_pthread_rwlock_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_rwlock_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat.Digit3[Nat._1, Nat._9, Nat._2]] = struct._2
      def __opaque_=(value: CArray[CChar, Nat.Digit3[Nat._1, Nat._9, Nat._2]]): Unit = !struct.at2 = value

  opaque type __darwin_pthread_rwlockattr_t = CStruct2[CLongInt, CArray[CChar, Nat.Digit2[Nat._1, Nat._6]]]
  object __darwin_pthread_rwlockattr_t:
    given _tag: Tag[__darwin_pthread_rwlockattr_t] = Tag.materializeCStruct2Tag[CLongInt, CArray[CChar, Nat.Digit2[Nat._1, Nat._6]]]
    def apply()(using Zone): Ptr[__darwin_pthread_rwlockattr_t] = scala.scalanative.unsafe.alloc[__darwin_pthread_rwlockattr_t](1)
    def apply(__sig : CLongInt, __opaque : CArray[CChar, Nat.Digit2[Nat._1, Nat._6]])(using Zone): Ptr[__darwin_pthread_rwlockattr_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: __darwin_pthread_rwlockattr_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __opaque : CArray[CChar, Nat.Digit2[Nat._1, Nat._6]] = struct._2
      def __opaque_=(value: CArray[CChar, Nat.Digit2[Nat._1, Nat._6]]): Unit = !struct.at2 = value

  opaque type __msfilterreq = CStruct6[uint32_t, uint32_t, uint32_t, uint32_t, sockaddr_storage, Ptr[sockaddr_storage]]
  object __msfilterreq:
    given _tag: Tag[__msfilterreq] = Tag.materializeCStruct6Tag[uint32_t, uint32_t, uint32_t, uint32_t, sockaddr_storage, Ptr[sockaddr_storage]]
    def apply()(using Zone): Ptr[__msfilterreq] = scala.scalanative.unsafe.alloc[__msfilterreq](1)
    def apply(msfr_ifindex : uint32_t, msfr_fmode : uint32_t, msfr_nsrcs : uint32_t, __msfr_align : uint32_t, msfr_group : sockaddr_storage, msfr_srcs : Ptr[sockaddr_storage])(using Zone): Ptr[__msfilterreq] = 
      val ____ptr = apply()
      (!____ptr).msfr_ifindex = msfr_ifindex
      (!____ptr).msfr_fmode = msfr_fmode
      (!____ptr).msfr_nsrcs = msfr_nsrcs
      (!____ptr).__msfr_align = __msfr_align
      (!____ptr).msfr_group = msfr_group
      (!____ptr).msfr_srcs = msfr_srcs
      ____ptr
    extension (struct: __msfilterreq)
      def msfr_ifindex : uint32_t = struct._1
      def msfr_ifindex_=(value: uint32_t): Unit = !struct.at1 = value
      def msfr_fmode : uint32_t = struct._2
      def msfr_fmode_=(value: uint32_t): Unit = !struct.at2 = value
      def msfr_nsrcs : uint32_t = struct._3
      def msfr_nsrcs_=(value: uint32_t): Unit = !struct.at3 = value
      def __msfr_align : uint32_t = struct._4
      def __msfr_align_=(value: uint32_t): Unit = !struct.at4 = value
      def msfr_group : sockaddr_storage = struct._5
      def msfr_group_=(value: sockaddr_storage): Unit = !struct.at5 = value
      def msfr_srcs : Ptr[sockaddr_storage] = struct._6
      def msfr_srcs_=(value: Ptr[sockaddr_storage]): Unit = !struct.at6 = value

  opaque type _opaque_pthread_t = CStruct3[CLongInt, Ptr[Byte], CArray[CChar, Nat.Digit4[Nat._8, Nat._1, Nat._7, Nat._6]]]
  object _opaque_pthread_t:
    given _tag: Tag[_opaque_pthread_t] = Tag.materializeCStruct3Tag[CLongInt, Ptr[Byte], CArray[CChar, Nat.Digit4[Nat._8, Nat._1, Nat._7, Nat._6]]]
    def apply()(using Zone): Ptr[_opaque_pthread_t] = scala.scalanative.unsafe.alloc[_opaque_pthread_t](1)
    def apply(__sig : CLongInt, __cleanup_stack : Ptr[__darwin_pthread_handler_rec], __opaque : CArray[CChar, Nat.Digit4[Nat._8, Nat._1, Nat._7, Nat._6]])(using Zone): Ptr[_opaque_pthread_t] = 
      val ____ptr = apply()
      (!____ptr).__sig = __sig
      (!____ptr).__cleanup_stack = __cleanup_stack
      (!____ptr).__opaque = __opaque
      ____ptr
    extension (struct: _opaque_pthread_t)
      def __sig : CLongInt = struct._1
      def __sig_=(value: CLongInt): Unit = !struct.at1 = value
      def __cleanup_stack : Ptr[__darwin_pthread_handler_rec] = struct._2.asInstanceOf[Ptr[__darwin_pthread_handler_rec]]
      def __cleanup_stack_=(value: Ptr[__darwin_pthread_handler_rec]): Unit = !struct.at2 = value.asInstanceOf[Ptr[Byte]]
      def __opaque : CArray[CChar, Nat.Digit4[Nat._8, Nat._1, Nat._7, Nat._6]] = struct._3
      def __opaque_=(value: CArray[CChar, Nat.Digit4[Nat._8, Nat._1, Nat._7, Nat._6]]): Unit = !struct.at3 = value

  opaque type addrinfo = CStruct8[CInt, CInt, CInt, CInt, socklen_t, CString, Ptr[sockaddr], Ptr[Byte]]
  object addrinfo:
    given _tag: Tag[addrinfo] = Tag.materializeCStruct8Tag[CInt, CInt, CInt, CInt, socklen_t, CString, Ptr[sockaddr], Ptr[Byte]]
    def apply()(using Zone): Ptr[addrinfo] = scala.scalanative.unsafe.alloc[addrinfo](1)
    def apply(ai_flags : CInt, ai_family : CInt, ai_socktype : CInt, ai_protocol : CInt, ai_addrlen : socklen_t, ai_canonname : CString, ai_addr : Ptr[sockaddr], ai_next : Ptr[addrinfo])(using Zone): Ptr[addrinfo] = 
      val ____ptr = apply()
      (!____ptr).ai_flags = ai_flags
      (!____ptr).ai_family = ai_family
      (!____ptr).ai_socktype = ai_socktype
      (!____ptr).ai_protocol = ai_protocol
      (!____ptr).ai_addrlen = ai_addrlen
      (!____ptr).ai_canonname = ai_canonname
      (!____ptr).ai_addr = ai_addr
      (!____ptr).ai_next = ai_next
      ____ptr
    extension (struct: addrinfo)
      def ai_flags : CInt = struct._1
      def ai_flags_=(value: CInt): Unit = !struct.at1 = value
      def ai_family : CInt = struct._2
      def ai_family_=(value: CInt): Unit = !struct.at2 = value
      def ai_socktype : CInt = struct._3
      def ai_socktype_=(value: CInt): Unit = !struct.at3 = value
      def ai_protocol : CInt = struct._4
      def ai_protocol_=(value: CInt): Unit = !struct.at4 = value
      def ai_addrlen : socklen_t = struct._5
      def ai_addrlen_=(value: socklen_t): Unit = !struct.at5 = value
      def ai_canonname : CString = struct._6
      def ai_canonname_=(value: CString): Unit = !struct.at6 = value
      def ai_addr : Ptr[sockaddr] = struct._7
      def ai_addr_=(value: Ptr[sockaddr]): Unit = !struct.at7 = value
      def ai_next : Ptr[addrinfo] = struct._8.asInstanceOf[Ptr[addrinfo]]
      def ai_next_=(value: Ptr[addrinfo]): Unit = !struct.at8 = value.asInstanceOf[Ptr[Byte]]

  opaque type bpf_insn = CStruct4[u_short, u_char, u_char, bpf_u_int32]
  object bpf_insn:
    given _tag: Tag[bpf_insn] = Tag.materializeCStruct4Tag[u_short, u_char, u_char, bpf_u_int32]
    def apply()(using Zone): Ptr[bpf_insn] = scala.scalanative.unsafe.alloc[bpf_insn](1)
    def apply(code : u_short, jt : u_char, jf : u_char, k : bpf_u_int32)(using Zone): Ptr[bpf_insn] = 
      val ____ptr = apply()
      (!____ptr).code = code
      (!____ptr).jt = jt
      (!____ptr).jf = jf
      (!____ptr).k = k
      ____ptr
    extension (struct: bpf_insn)
      def code : u_short = struct._1
      def code_=(value: u_short): Unit = !struct.at1 = value
      def jt : u_char = struct._2
      def jt_=(value: u_char): Unit = !struct.at2 = value
      def jf : u_char = struct._3
      def jf_=(value: u_char): Unit = !struct.at3 = value
      def k : bpf_u_int32 = struct._4
      def k_=(value: bpf_u_int32): Unit = !struct.at4 = value

  opaque type bpf_program = CStruct2[u_int, Ptr[bpf_insn]]
  object bpf_program:
    opaque type Struct0 = CStruct0
    object Struct0:
      given _tag: Tag[Struct0] = Tag.materializeCStruct0Tag
    given _tag: Tag[bpf_program] = Tag.materializeCStruct2Tag[u_int, Ptr[bpf_insn]]
    def apply()(using Zone): Ptr[bpf_program] = scala.scalanative.unsafe.alloc[bpf_program](1)
    def apply(bf_len : u_int, bf_insns : Ptr[bpf_insn])(using Zone): Ptr[bpf_program] = 
      val ____ptr = apply()
      (!____ptr).bf_len = bf_len
      (!____ptr).bf_insns = bf_insns
      ____ptr
    extension (struct: bpf_program)
      def bf_len : u_int = struct._1
      def bf_len_=(value: u_int): Unit = !struct.at1 = value
      def bf_insns : Ptr[bpf_insn] = struct._2
      def bf_insns_=(value: Ptr[bpf_insn]): Unit = !struct.at2 = value

  opaque type clockinfo = CStruct5[CInt, CInt, CInt, CInt, CInt]
  object clockinfo:
    given _tag: Tag[clockinfo] = Tag.materializeCStruct5Tag[CInt, CInt, CInt, CInt, CInt]
    def apply()(using Zone): Ptr[clockinfo] = scala.scalanative.unsafe.alloc[clockinfo](1)
    def apply(hz : CInt, tick : CInt, tickadj : CInt, stathz : CInt, profhz : CInt)(using Zone): Ptr[clockinfo] = 
      val ____ptr = apply()
      (!____ptr).hz = hz
      (!____ptr).tick = tick
      (!____ptr).tickadj = tickadj
      (!____ptr).stathz = stathz
      (!____ptr).profhz = profhz
      ____ptr
    extension (struct: clockinfo)
      def hz : CInt = struct._1
      def hz_=(value: CInt): Unit = !struct.at1 = value
      def tick : CInt = struct._2
      def tick_=(value: CInt): Unit = !struct.at2 = value
      def tickadj : CInt = struct._3
      def tickadj_=(value: CInt): Unit = !struct.at3 = value
      def stathz : CInt = struct._4
      def stathz_=(value: CInt): Unit = !struct.at4 = value
      def profhz : CInt = struct._5
      def profhz_=(value: CInt): Unit = !struct.at5 = value

  opaque type cmsghdr = CStruct0
  object cmsghdr:
    given _tag: Tag[cmsghdr] = Tag.materializeCStruct0Tag

  opaque type fd_set = CStruct1[CArray[__int32_t, Nat.Digit2[Nat._3, Nat._2]]]
  object fd_set:
    given _tag: Tag[fd_set] = Tag.materializeCStruct1Tag[CArray[__int32_t, Nat.Digit2[Nat._3, Nat._2]]]
    def apply()(using Zone): Ptr[fd_set] = scala.scalanative.unsafe.alloc[fd_set](1)
    def apply(fds_bits : CArray[__int32_t, Nat.Digit2[Nat._3, Nat._2]])(using Zone): Ptr[fd_set] = 
      val ____ptr = apply()
      (!____ptr).fds_bits = fds_bits
      ____ptr
    extension (struct: fd_set)
      def fds_bits : CArray[__int32_t, Nat.Digit2[Nat._3, Nat._2]] = struct._1
      def fds_bits_=(value: CArray[__int32_t, Nat.Digit2[Nat._3, Nat._2]]): Unit = !struct.at1 = value

  opaque type group_req = CStruct2[uint32_t, sockaddr_storage]
  object group_req:
    given _tag: Tag[group_req] = Tag.materializeCStruct2Tag[uint32_t, sockaddr_storage]
    def apply()(using Zone): Ptr[group_req] = scala.scalanative.unsafe.alloc[group_req](1)
    def apply(gr_interface : uint32_t, gr_group : sockaddr_storage)(using Zone): Ptr[group_req] = 
      val ____ptr = apply()
      (!____ptr).gr_interface = gr_interface
      (!____ptr).gr_group = gr_group
      ____ptr
    extension (struct: group_req)
      def gr_interface : uint32_t = struct._1
      def gr_interface_=(value: uint32_t): Unit = !struct.at1 = value
      def gr_group : sockaddr_storage = struct._2
      def gr_group_=(value: sockaddr_storage): Unit = !struct.at2 = value

  opaque type group_source_req = CStruct3[uint32_t, sockaddr_storage, sockaddr_storage]
  object group_source_req:
    given _tag: Tag[group_source_req] = Tag.materializeCStruct3Tag[uint32_t, sockaddr_storage, sockaddr_storage]
    def apply()(using Zone): Ptr[group_source_req] = scala.scalanative.unsafe.alloc[group_source_req](1)
    def apply(gsr_interface : uint32_t, gsr_group : sockaddr_storage, gsr_source : sockaddr_storage)(using Zone): Ptr[group_source_req] = 
      val ____ptr = apply()
      (!____ptr).gsr_interface = gsr_interface
      (!____ptr).gsr_group = gsr_group
      (!____ptr).gsr_source = gsr_source
      ____ptr
    extension (struct: group_source_req)
      def gsr_interface : uint32_t = struct._1
      def gsr_interface_=(value: uint32_t): Unit = !struct.at1 = value
      def gsr_group : sockaddr_storage = struct._2
      def gsr_group_=(value: sockaddr_storage): Unit = !struct.at2 = value
      def gsr_source : sockaddr_storage = struct._3
      def gsr_source_=(value: sockaddr_storage): Unit = !struct.at3 = value

  opaque type hostent = CStruct5[CString, Ptr[CString], CInt, CInt, Ptr[CString]]
  object hostent:
    given _tag: Tag[hostent] = Tag.materializeCStruct5Tag[CString, Ptr[CString], CInt, CInt, Ptr[CString]]
    def apply()(using Zone): Ptr[hostent] = scala.scalanative.unsafe.alloc[hostent](1)
    def apply(h_name : CString, h_aliases : Ptr[CString], h_addrtype : CInt, h_length : CInt, h_addr_list : Ptr[CString])(using Zone): Ptr[hostent] = 
      val ____ptr = apply()
      (!____ptr).h_name = h_name
      (!____ptr).h_aliases = h_aliases
      (!____ptr).h_addrtype = h_addrtype
      (!____ptr).h_length = h_length
      (!____ptr).h_addr_list = h_addr_list
      ____ptr
    extension (struct: hostent)
      def h_name : CString = struct._1
      def h_name_=(value: CString): Unit = !struct.at1 = value
      def h_aliases : Ptr[CString] = struct._2
      def h_aliases_=(value: Ptr[CString]): Unit = !struct.at2 = value
      def h_addrtype : CInt = struct._3
      def h_addrtype_=(value: CInt): Unit = !struct.at3 = value
      def h_length : CInt = struct._4
      def h_length_=(value: CInt): Unit = !struct.at4 = value
      def h_addr_list : Ptr[CString] = struct._5
      def h_addr_list_=(value: Ptr[CString]): Unit = !struct.at5 = value

  opaque type in6_addr = CStruct1[in6_addr.Union0]
  object in6_addr:
    opaque type Union0 = CArray[Byte, Nat.Digit2[Nat._1, Nat._6]]
    object Union0:
      given _tag: Tag[Union0] = Tag.CArray[CChar, Nat.Digit2[Nat._1, Nat._6]](Tag.Byte, Tag.Digit2[Nat._1, Nat._6](Tag.Nat1, Tag.Nat6))
      def apply()(using Zone): Ptr[Union0] = 
        val ___ptr = alloc[Union0](1)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr8")
      def apply(__u6_addr8: CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]].update(0, __u6_addr8)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr16")
      def apply(__u6_addr16: CArray[__uint16_t, Nat._8])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]].update(0, __u6_addr16)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr32")
      def apply(__u6_addr32: CArray[__uint32_t, Nat._4])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]].update(0, __u6_addr32)
        ___ptr
      extension (struct: Union0)
        def __u6_addr8 : CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]]
        def __u6_addr8_=(value: CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]] = value
        def __u6_addr16 : CArray[__uint16_t, Nat._8] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]]
        def __u6_addr16_=(value: CArray[__uint16_t, Nat._8]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]] = value
        def __u6_addr32 : CArray[__uint32_t, Nat._4] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]]
        def __u6_addr32_=(value: CArray[__uint32_t, Nat._4]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]] = value
    given _tag: Tag[in6_addr] = Tag.materializeCStruct1Tag[in6_addr.Union0]
    def apply()(using Zone): Ptr[in6_addr] = scala.scalanative.unsafe.alloc[in6_addr](1)
    def apply(__u6_addr : in6_addr.Union0)(using Zone): Ptr[in6_addr] = 
      val ____ptr = apply()
      (!____ptr).__u6_addr = __u6_addr
      ____ptr
    extension (struct: in6_addr)
      def __u6_addr : in6_addr.Union0 = struct._1
      def __u6_addr_=(value: in6_addr.Union0): Unit = !struct.at1 = value

  opaque type in6_addr_t = CStruct1[in6_addr_t.Union0]
  object in6_addr_t:
    opaque type Union0 = CArray[Byte, Nat.Digit2[Nat._1, Nat._6]]
    object Union0:
      given _tag: Tag[Union0] = Tag.CArray[CChar, Nat.Digit2[Nat._1, Nat._6]](Tag.Byte, Tag.Digit2[Nat._1, Nat._6](Tag.Nat1, Tag.Nat6))
      def apply()(using Zone): Ptr[Union0] = 
        val ___ptr = alloc[Union0](1)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr8")
      def apply(__u6_addr8: CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]].update(0, __u6_addr8)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr16")
      def apply(__u6_addr16: CArray[__uint16_t, Nat._8])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]].update(0, __u6_addr16)
        ___ptr
      @scala.annotation.targetName("apply___u6_addr32")
      def apply(__u6_addr32: CArray[__uint32_t, Nat._4])(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]].update(0, __u6_addr32)
        ___ptr
      extension (struct: Union0)
        def __u6_addr8 : CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]]
        def __u6_addr8_=(value: CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint8_t, Nat.Digit2[Nat._1, Nat._6]]]] = value
        def __u6_addr16 : CArray[__uint16_t, Nat._8] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]]
        def __u6_addr16_=(value: CArray[__uint16_t, Nat._8]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint16_t, Nat._8]]] = value
        def __u6_addr32 : CArray[__uint32_t, Nat._4] = !struct.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]]
        def __u6_addr32_=(value: CArray[__uint32_t, Nat._4]): Unit = !struct.at(0).asInstanceOf[Ptr[CArray[__uint32_t, Nat._4]]] = value
    given _tag: Tag[in6_addr_t] = Tag.materializeCStruct1Tag[in6_addr_t.Union0]
    def apply()(using Zone): Ptr[in6_addr_t] = scala.scalanative.unsafe.alloc[in6_addr_t](1)
    def apply(__u6_addr : in6_addr_t.Union0)(using Zone): Ptr[in6_addr_t] = 
      val ____ptr = apply()
      (!____ptr).__u6_addr = __u6_addr
      ____ptr
    extension (struct: in6_addr_t)
      def __u6_addr : in6_addr_t.Union0 = struct._1
      def __u6_addr_=(value: in6_addr_t.Union0): Unit = !struct.at1 = value

  opaque type in6_pktinfo = CStruct2[in6_addr, CUnsignedInt]
  object in6_pktinfo:
    given _tag: Tag[in6_pktinfo] = Tag.materializeCStruct2Tag[in6_addr, CUnsignedInt]
    def apply()(using Zone): Ptr[in6_pktinfo] = scala.scalanative.unsafe.alloc[in6_pktinfo](1)
    def apply(ipi6_addr : in6_addr, ipi6_ifindex : CUnsignedInt)(using Zone): Ptr[in6_pktinfo] = 
      val ____ptr = apply()
      (!____ptr).ipi6_addr = ipi6_addr
      (!____ptr).ipi6_ifindex = ipi6_ifindex
      ____ptr
    extension (struct: in6_pktinfo)
      def ipi6_addr : in6_addr = struct._1
      def ipi6_addr_=(value: in6_addr): Unit = !struct.at1 = value
      def ipi6_ifindex : CUnsignedInt = struct._2
      def ipi6_ifindex_=(value: CUnsignedInt): Unit = !struct.at2 = value

  opaque type in_addr = CStruct1[in_addr_t]
  object in_addr:
    given _tag: Tag[in_addr] = Tag.materializeCStruct1Tag[in_addr_t]
    def apply()(using Zone): Ptr[in_addr] = scala.scalanative.unsafe.alloc[in_addr](1)
    def apply(s_addr : in_addr_t)(using Zone): Ptr[in_addr] = 
      val ____ptr = apply()
      (!____ptr).s_addr = s_addr
      ____ptr
    extension (struct: in_addr)
      def s_addr : in_addr_t = struct._1
      def s_addr_=(value: in_addr_t): Unit = !struct.at1 = value

  opaque type in_pktinfo = CStruct3[CUnsignedInt, in_addr, in_addr]
  object in_pktinfo:
    given _tag: Tag[in_pktinfo] = Tag.materializeCStruct3Tag[CUnsignedInt, in_addr, in_addr]
    def apply()(using Zone): Ptr[in_pktinfo] = scala.scalanative.unsafe.alloc[in_pktinfo](1)
    def apply(ipi_ifindex : CUnsignedInt, ipi_spec_dst : in_addr, ipi_addr : in_addr)(using Zone): Ptr[in_pktinfo] = 
      val ____ptr = apply()
      (!____ptr).ipi_ifindex = ipi_ifindex
      (!____ptr).ipi_spec_dst = ipi_spec_dst
      (!____ptr).ipi_addr = ipi_addr
      ____ptr
    extension (struct: in_pktinfo)
      def ipi_ifindex : CUnsignedInt = struct._1
      def ipi_ifindex_=(value: CUnsignedInt): Unit = !struct.at1 = value
      def ipi_spec_dst : in_addr = struct._2
      def ipi_spec_dst_=(value: in_addr): Unit = !struct.at2 = value
      def ipi_addr : in_addr = struct._3
      def ipi_addr_=(value: in_addr): Unit = !struct.at3 = value

  opaque type iovec = CStruct2[Ptr[Byte], size_t]
  object iovec:
    given _tag: Tag[iovec] = Tag.materializeCStruct2Tag[Ptr[Byte], size_t]
    def apply()(using Zone): Ptr[iovec] = scala.scalanative.unsafe.alloc[iovec](1)
    def apply(iov_base : Ptr[Byte], iov_len : size_t)(using Zone): Ptr[iovec] = 
      val ____ptr = apply()
      (!____ptr).iov_base = iov_base
      (!____ptr).iov_len = iov_len
      ____ptr
    extension (struct: iovec)
      def iov_base : Ptr[Byte] = struct._1
      def iov_base_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def iov_len : size_t = struct._2
      def iov_len_=(value: size_t): Unit = !struct.at2 = value

  opaque type ip6_mtuinfo = CStruct2[sockaddr_in6, uint32_t]
  object ip6_mtuinfo:
    given _tag: Tag[ip6_mtuinfo] = Tag.materializeCStruct2Tag[sockaddr_in6, uint32_t]
    def apply()(using Zone): Ptr[ip6_mtuinfo] = scala.scalanative.unsafe.alloc[ip6_mtuinfo](1)
    def apply(ip6m_addr : sockaddr_in6, ip6m_mtu : uint32_t)(using Zone): Ptr[ip6_mtuinfo] = 
      val ____ptr = apply()
      (!____ptr).ip6m_addr = ip6m_addr
      (!____ptr).ip6m_mtu = ip6m_mtu
      ____ptr
    extension (struct: ip6_mtuinfo)
      def ip6m_addr : sockaddr_in6 = struct._1
      def ip6m_addr_=(value: sockaddr_in6): Unit = !struct.at1 = value
      def ip6m_mtu : uint32_t = struct._2
      def ip6m_mtu_=(value: uint32_t): Unit = !struct.at2 = value

  opaque type ip_mreq = CStruct2[in_addr, in_addr]
  object ip_mreq:
    given _tag: Tag[ip_mreq] = Tag.materializeCStruct2Tag[in_addr, in_addr]
    def apply()(using Zone): Ptr[ip_mreq] = scala.scalanative.unsafe.alloc[ip_mreq](1)
    def apply(imr_multiaddr : in_addr, imr_interface : in_addr)(using Zone): Ptr[ip_mreq] = 
      val ____ptr = apply()
      (!____ptr).imr_multiaddr = imr_multiaddr
      (!____ptr).imr_interface = imr_interface
      ____ptr
    extension (struct: ip_mreq)
      def imr_multiaddr : in_addr = struct._1
      def imr_multiaddr_=(value: in_addr): Unit = !struct.at1 = value
      def imr_interface : in_addr = struct._2
      def imr_interface_=(value: in_addr): Unit = !struct.at2 = value

  opaque type ip_mreq_source = CStruct3[in_addr, in_addr, in_addr]
  object ip_mreq_source:
    given _tag: Tag[ip_mreq_source] = Tag.materializeCStruct3Tag[in_addr, in_addr, in_addr]
    def apply()(using Zone): Ptr[ip_mreq_source] = scala.scalanative.unsafe.alloc[ip_mreq_source](1)
    def apply(imr_multiaddr : in_addr, imr_sourceaddr : in_addr, imr_interface : in_addr)(using Zone): Ptr[ip_mreq_source] = 
      val ____ptr = apply()
      (!____ptr).imr_multiaddr = imr_multiaddr
      (!____ptr).imr_sourceaddr = imr_sourceaddr
      (!____ptr).imr_interface = imr_interface
      ____ptr
    extension (struct: ip_mreq_source)
      def imr_multiaddr : in_addr = struct._1
      def imr_multiaddr_=(value: in_addr): Unit = !struct.at1 = value
      def imr_sourceaddr : in_addr = struct._2
      def imr_sourceaddr_=(value: in_addr): Unit = !struct.at2 = value
      def imr_interface : in_addr = struct._3
      def imr_interface_=(value: in_addr): Unit = !struct.at3 = value

  opaque type ip_mreqn = CStruct3[in_addr, in_addr, CInt]
  object ip_mreqn:
    given _tag: Tag[ip_mreqn] = Tag.materializeCStruct3Tag[in_addr, in_addr, CInt]
    def apply()(using Zone): Ptr[ip_mreqn] = scala.scalanative.unsafe.alloc[ip_mreqn](1)
    def apply(imr_multiaddr : in_addr, imr_address : in_addr, imr_ifindex : CInt)(using Zone): Ptr[ip_mreqn] = 
      val ____ptr = apply()
      (!____ptr).imr_multiaddr = imr_multiaddr
      (!____ptr).imr_address = imr_address
      (!____ptr).imr_ifindex = imr_ifindex
      ____ptr
    extension (struct: ip_mreqn)
      def imr_multiaddr : in_addr = struct._1
      def imr_multiaddr_=(value: in_addr): Unit = !struct.at1 = value
      def imr_address : in_addr = struct._2
      def imr_address_=(value: in_addr): Unit = !struct.at2 = value
      def imr_ifindex : CInt = struct._3
      def imr_ifindex_=(value: CInt): Unit = !struct.at3 = value

  opaque type ip_opts = CStruct2[in_addr, CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]]
  object ip_opts:
    given _tag: Tag[ip_opts] = Tag.materializeCStruct2Tag[in_addr, CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]]
    def apply()(using Zone): Ptr[ip_opts] = scala.scalanative.unsafe.alloc[ip_opts](1)
    def apply(ip_dst : in_addr, ip_opts : CArray[CChar, Nat.Digit2[Nat._4, Nat._0]])(using Zone): Ptr[ip_opts] = 
      val ____ptr = apply()
      (!____ptr).ip_dst = ip_dst
      (!____ptr).ip_opts = ip_opts
      ____ptr
    extension (struct: ip_opts)
      def ip_dst : in_addr = struct._1
      def ip_dst_=(value: in_addr): Unit = !struct.at1 = value
      def ip_opts : CArray[CChar, Nat.Digit2[Nat._4, Nat._0]] = struct._2
      def ip_opts_=(value: CArray[CChar, Nat.Digit2[Nat._4, Nat._0]]): Unit = !struct.at2 = value

  opaque type ipv6_mreq = CStruct2[in6_addr, CUnsignedInt]
  object ipv6_mreq:
    given _tag: Tag[ipv6_mreq] = Tag.materializeCStruct2Tag[in6_addr, CUnsignedInt]
    def apply()(using Zone): Ptr[ipv6_mreq] = scala.scalanative.unsafe.alloc[ipv6_mreq](1)
    def apply(ipv6mr_multiaddr : in6_addr, ipv6mr_interface : CUnsignedInt)(using Zone): Ptr[ipv6_mreq] = 
      val ____ptr = apply()
      (!____ptr).ipv6mr_multiaddr = ipv6mr_multiaddr
      (!____ptr).ipv6mr_interface = ipv6mr_interface
      ____ptr
    extension (struct: ipv6_mreq)
      def ipv6mr_multiaddr : in6_addr = struct._1
      def ipv6mr_multiaddr_=(value: in6_addr): Unit = !struct.at1 = value
      def ipv6mr_interface : CUnsignedInt = struct._2
      def ipv6mr_interface_=(value: CUnsignedInt): Unit = !struct.at2 = value

  opaque type itimerval = CStruct2[timeval, timeval]
  object itimerval:
    given _tag: Tag[itimerval] = Tag.materializeCStruct2Tag[timeval, timeval]
    def apply()(using Zone): Ptr[itimerval] = scala.scalanative.unsafe.alloc[itimerval](1)
    def apply(it_interval : timeval, it_value : timeval)(using Zone): Ptr[itimerval] = 
      val ____ptr = apply()
      (!____ptr).it_interval = it_interval
      (!____ptr).it_value = it_value
      ____ptr
    extension (struct: itimerval)
      def it_interval : timeval = struct._1
      def it_interval_=(value: timeval): Unit = !struct.at1 = value
      def it_value : timeval = struct._2
      def it_value_=(value: timeval): Unit = !struct.at2 = value

  opaque type linger = CStruct2[CInt, CInt]
  object linger:
    given _tag: Tag[linger] = Tag.materializeCStruct2Tag[CInt, CInt]
    def apply()(using Zone): Ptr[linger] = scala.scalanative.unsafe.alloc[linger](1)
    def apply(l_onoff : CInt, l_linger : CInt)(using Zone): Ptr[linger] = 
      val ____ptr = apply()
      (!____ptr).l_onoff = l_onoff
      (!____ptr).l_linger = l_linger
      ____ptr
    extension (struct: linger)
      def l_onoff : CInt = struct._1
      def l_onoff_=(value: CInt): Unit = !struct.at1 = value
      def l_linger : CInt = struct._2
      def l_linger_=(value: CInt): Unit = !struct.at2 = value

  opaque type msghdr = CStruct7[Ptr[Byte], socklen_t, Ptr[iovec], CInt, Ptr[Byte], socklen_t, CInt]
  object msghdr:
    given _tag: Tag[msghdr] = Tag.materializeCStruct7Tag[Ptr[Byte], socklen_t, Ptr[iovec], CInt, Ptr[Byte], socklen_t, CInt]
    def apply()(using Zone): Ptr[msghdr] = scala.scalanative.unsafe.alloc[msghdr](1)
    def apply(msg_name : Ptr[Byte], msg_namelen : socklen_t, msg_iov : Ptr[iovec], msg_iovlen : CInt, msg_control : Ptr[Byte], msg_controllen : socklen_t, msg_flags : CInt)(using Zone): Ptr[msghdr] = 
      val ____ptr = apply()
      (!____ptr).msg_name = msg_name
      (!____ptr).msg_namelen = msg_namelen
      (!____ptr).msg_iov = msg_iov
      (!____ptr).msg_iovlen = msg_iovlen
      (!____ptr).msg_control = msg_control
      (!____ptr).msg_controllen = msg_controllen
      (!____ptr).msg_flags = msg_flags
      ____ptr
    extension (struct: msghdr)
      def msg_name : Ptr[Byte] = struct._1
      def msg_name_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def msg_namelen : socklen_t = struct._2
      def msg_namelen_=(value: socklen_t): Unit = !struct.at2 = value
      def msg_iov : Ptr[iovec] = struct._3
      def msg_iov_=(value: Ptr[iovec]): Unit = !struct.at3 = value
      def msg_iovlen : CInt = struct._4
      def msg_iovlen_=(value: CInt): Unit = !struct.at4 = value
      def msg_control : Ptr[Byte] = struct._5
      def msg_control_=(value: Ptr[Byte]): Unit = !struct.at5 = value
      def msg_controllen : socklen_t = struct._6
      def msg_controllen_=(value: socklen_t): Unit = !struct.at6 = value
      def msg_flags : CInt = struct._7
      def msg_flags_=(value: CInt): Unit = !struct.at7 = value

  opaque type netent = CStruct4[CString, Ptr[CString], CInt, uint32_t]
  object netent:
    given _tag: Tag[netent] = Tag.materializeCStruct4Tag[CString, Ptr[CString], CInt, uint32_t]
    def apply()(using Zone): Ptr[netent] = scala.scalanative.unsafe.alloc[netent](1)
    def apply(n_name : CString, n_aliases : Ptr[CString], n_addrtype : CInt, n_net : uint32_t)(using Zone): Ptr[netent] = 
      val ____ptr = apply()
      (!____ptr).n_name = n_name
      (!____ptr).n_aliases = n_aliases
      (!____ptr).n_addrtype = n_addrtype
      (!____ptr).n_net = n_net
      ____ptr
    extension (struct: netent)
      def n_name : CString = struct._1
      def n_name_=(value: CString): Unit = !struct.at1 = value
      def n_aliases : Ptr[CString] = struct._2
      def n_aliases_=(value: Ptr[CString]): Unit = !struct.at2 = value
      def n_addrtype : CInt = struct._3
      def n_addrtype_=(value: CInt): Unit = !struct.at3 = value
      def n_net : uint32_t = struct._4
      def n_net_=(value: uint32_t): Unit = !struct.at4 = value

  opaque type pcap = CStruct0
  object pcap:
    given _tag: Tag[pcap] = Tag.materializeCStruct0Tag

  opaque type pcap_addr = CStruct5[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
  object pcap_addr:
    given _tag: Tag[pcap_addr] = Tag.materializeCStruct5Tag[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
    def apply()(using Zone): Ptr[pcap_addr] = scala.scalanative.unsafe.alloc[pcap_addr](1)
    def apply(next : Ptr[pcap_addr], addr : Ptr[sockaddr], netmask : Ptr[sockaddr], broadaddr : Ptr[sockaddr], dstaddr : Ptr[sockaddr])(using Zone): Ptr[pcap_addr] = 
      val ____ptr = apply()
      (!____ptr).next = next
      (!____ptr).addr = addr
      (!____ptr).netmask = netmask
      (!____ptr).broadaddr = broadaddr
      (!____ptr).dstaddr = dstaddr
      ____ptr
    extension (struct: pcap_addr)
      def next : Ptr[pcap_addr] = struct._1.asInstanceOf[Ptr[pcap_addr]]
      def next_=(value: Ptr[pcap_addr]): Unit = !struct.at1 = value.asInstanceOf[Ptr[Byte]]
      def addr : Ptr[sockaddr] = struct._2
      def addr_=(value: Ptr[sockaddr]): Unit = !struct.at2 = value
      def netmask : Ptr[sockaddr] = struct._3
      def netmask_=(value: Ptr[sockaddr]): Unit = !struct.at3 = value
      def broadaddr : Ptr[sockaddr] = struct._4
      def broadaddr_=(value: Ptr[sockaddr]): Unit = !struct.at4 = value
      def dstaddr : Ptr[sockaddr] = struct._5
      def dstaddr_=(value: Ptr[sockaddr]): Unit = !struct.at5 = value

  opaque type pcap_addr_t = CStruct5[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
  object pcap_addr_t:
    given _tag: Tag[pcap_addr_t] = Tag.materializeCStruct5Tag[Ptr[Byte], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr], Ptr[sockaddr]]
    def apply()(using Zone): Ptr[pcap_addr_t] = scala.scalanative.unsafe.alloc[pcap_addr_t](1)
    def apply(next : Ptr[pcap_addr], addr : Ptr[sockaddr], netmask : Ptr[sockaddr], broadaddr : Ptr[sockaddr], dstaddr : Ptr[sockaddr])(using Zone): Ptr[pcap_addr_t] = 
      val ____ptr = apply()
      (!____ptr).next = next
      (!____ptr).addr = addr
      (!____ptr).netmask = netmask
      (!____ptr).broadaddr = broadaddr
      (!____ptr).dstaddr = dstaddr
      ____ptr
    extension (struct: pcap_addr_t)
      def next : Ptr[pcap_addr] = struct._1.asInstanceOf[Ptr[pcap_addr]]
      def next_=(value: Ptr[pcap_addr]): Unit = !struct.at1 = value.asInstanceOf[Ptr[Byte]]
      def addr : Ptr[sockaddr] = struct._2
      def addr_=(value: Ptr[sockaddr]): Unit = !struct.at2 = value
      def netmask : Ptr[sockaddr] = struct._3
      def netmask_=(value: Ptr[sockaddr]): Unit = !struct.at3 = value
      def broadaddr : Ptr[sockaddr] = struct._4
      def broadaddr_=(value: Ptr[sockaddr]): Unit = !struct.at4 = value
      def dstaddr : Ptr[sockaddr] = struct._5
      def dstaddr_=(value: Ptr[sockaddr]): Unit = !struct.at5 = value

  opaque type pcap_dumper = CStruct0
  object pcap_dumper:
    given _tag: Tag[pcap_dumper] = Tag.materializeCStruct0Tag

  opaque type pcap_dumper_t = CStruct0
  object pcap_dumper_t:
    given _tag: Tag[pcap_dumper_t] = Tag.materializeCStruct0Tag

  opaque type pcap_file_header = CStruct7[bpf_u_int32, u_short, u_short, bpf_int32, bpf_u_int32, bpf_u_int32, bpf_u_int32]
  object pcap_file_header:
    given _tag: Tag[pcap_file_header] = Tag.materializeCStruct7Tag[bpf_u_int32, u_short, u_short, bpf_int32, bpf_u_int32, bpf_u_int32, bpf_u_int32]
    def apply()(using Zone): Ptr[pcap_file_header] = scala.scalanative.unsafe.alloc[pcap_file_header](1)
    def apply(magic : bpf_u_int32, version_major : u_short, version_minor : u_short, thiszone : bpf_int32, sigfigs : bpf_u_int32, snaplen : bpf_u_int32, linktype : bpf_u_int32)(using Zone): Ptr[pcap_file_header] = 
      val ____ptr = apply()
      (!____ptr).magic = magic
      (!____ptr).version_major = version_major
      (!____ptr).version_minor = version_minor
      (!____ptr).thiszone = thiszone
      (!____ptr).sigfigs = sigfigs
      (!____ptr).snaplen = snaplen
      (!____ptr).linktype = linktype
      ____ptr
    extension (struct: pcap_file_header)
      def magic : bpf_u_int32 = struct._1
      def magic_=(value: bpf_u_int32): Unit = !struct.at1 = value
      def version_major : u_short = struct._2
      def version_major_=(value: u_short): Unit = !struct.at2 = value
      def version_minor : u_short = struct._3
      def version_minor_=(value: u_short): Unit = !struct.at3 = value
      def thiszone : bpf_int32 = struct._4
      def thiszone_=(value: bpf_int32): Unit = !struct.at4 = value
      def sigfigs : bpf_u_int32 = struct._5
      def sigfigs_=(value: bpf_u_int32): Unit = !struct.at5 = value
      def snaplen : bpf_u_int32 = struct._6
      def snaplen_=(value: bpf_u_int32): Unit = !struct.at6 = value
      def linktype : bpf_u_int32 = struct._7
      def linktype_=(value: bpf_u_int32): Unit = !struct.at7 = value

  opaque type pcap_if = CStruct5[Ptr[Byte], CString, CString, Ptr[Byte], bpf_u_int32]
  object pcap_if:
    given _tag: Tag[pcap_if] = Tag.materializeCStruct5Tag[Ptr[Byte], CString, CString, Ptr[Byte], bpf_u_int32]
    def apply()(using Zone): Ptr[pcap_if] = scala.scalanative.unsafe.alloc[pcap_if](1)
    def apply(next : Ptr[pcap_if], name : CString, description : CString, addresses : Ptr[pcap_addr], flags : bpf_u_int32)(using Zone): Ptr[pcap_if] = 
      val ____ptr = apply()
      (!____ptr).next = next
      (!____ptr).name = name
      (!____ptr).description = description
      (!____ptr).addresses = addresses
      (!____ptr).flags = flags
      ____ptr
    extension (struct: pcap_if)
      def next : Ptr[pcap_if] = struct._1.asInstanceOf[Ptr[pcap_if]]
      def next_=(value: Ptr[pcap_if]): Unit = !struct.at1 = value.asInstanceOf[Ptr[Byte]]
      def name : CString = struct._2
      def name_=(value: CString): Unit = !struct.at2 = value
      def description : CString = struct._3
      def description_=(value: CString): Unit = !struct.at3 = value
      def addresses : Ptr[pcap_addr] = struct._4.asInstanceOf[Ptr[pcap_addr]]
      def addresses_=(value: Ptr[pcap_addr]): Unit = !struct.at4 = value.asInstanceOf[Ptr[Byte]]
      def flags : bpf_u_int32 = struct._5
      def flags_=(value: bpf_u_int32): Unit = !struct.at5 = value

  opaque type pcap_if_t = CStruct5[Ptr[Byte], CString, CString, Ptr[Byte], bpf_u_int32]
  object pcap_if_t:
    given _tag: Tag[pcap_if_t] = Tag.materializeCStruct5Tag[Ptr[Byte], CString, CString, Ptr[Byte], bpf_u_int32]
    def apply()(using Zone): Ptr[pcap_if_t] = scala.scalanative.unsafe.alloc[pcap_if_t](1)
    def apply(next : Ptr[pcap_if], name : CString, description : CString, addresses : Ptr[pcap_addr], flags : bpf_u_int32)(using Zone): Ptr[pcap_if_t] = 
      val ____ptr = apply()
      (!____ptr).next = next
      (!____ptr).name = name
      (!____ptr).description = description
      (!____ptr).addresses = addresses
      (!____ptr).flags = flags
      ____ptr
    extension (struct: pcap_if_t)
      def next : Ptr[pcap_if] = struct._1.asInstanceOf[Ptr[pcap_if]]
      def next_=(value: Ptr[pcap_if]): Unit = !struct.at1 = value.asInstanceOf[Ptr[Byte]]
      def name : CString = struct._2
      def name_=(value: CString): Unit = !struct.at2 = value
      def description : CString = struct._3
      def description_=(value: CString): Unit = !struct.at3 = value
      def addresses : Ptr[pcap_addr] = struct._4.asInstanceOf[Ptr[pcap_addr]]
      def addresses_=(value: Ptr[pcap_addr]): Unit = !struct.at4 = value.asInstanceOf[Ptr[Byte]]
      def flags : bpf_u_int32 = struct._5
      def flags_=(value: bpf_u_int32): Unit = !struct.at5 = value

  opaque type pcap_pkthdr = CStruct4[timeval, bpf_u_int32, bpf_u_int32, CArray[CChar, Nat.Digit3[Nat._2, Nat._5, Nat._6]]]
  object pcap_pkthdr:
    given _tag: Tag[pcap_pkthdr] = Tag.materializeCStruct4Tag[timeval, bpf_u_int32, bpf_u_int32, CArray[CChar, Nat.Digit3[Nat._2, Nat._5, Nat._6]]]
    def apply()(using Zone): Ptr[pcap_pkthdr] = scala.scalanative.unsafe.alloc[pcap_pkthdr](1)
    def apply(ts : timeval, caplen : bpf_u_int32, len : bpf_u_int32, comment : CArray[CChar, Nat.Digit3[Nat._2, Nat._5, Nat._6]])(using Zone): Ptr[pcap_pkthdr] = 
      val ____ptr = apply()
      (!____ptr).ts = ts
      (!____ptr).caplen = caplen
      (!____ptr).len = len
      (!____ptr).comment = comment
      ____ptr
    extension (struct: pcap_pkthdr)
      def ts : timeval = struct._1
      def ts_=(value: timeval): Unit = !struct.at1 = value
      def caplen : bpf_u_int32 = struct._2
      def caplen_=(value: bpf_u_int32): Unit = !struct.at2 = value
      def len : bpf_u_int32 = struct._3
      def len_=(value: bpf_u_int32): Unit = !struct.at3 = value
      def comment : CArray[CChar, Nat.Digit3[Nat._2, Nat._5, Nat._6]] = struct._4
      def comment_=(value: CArray[CChar, Nat.Digit3[Nat._2, Nat._5, Nat._6]]): Unit = !struct.at4 = value

  opaque type pcap_rmtauth = CStruct3[CInt, CString, CString]
  object pcap_rmtauth:
    given _tag: Tag[pcap_rmtauth] = Tag.materializeCStruct3Tag[CInt, CString, CString]
    def apply()(using Zone): Ptr[pcap_rmtauth] = scala.scalanative.unsafe.alloc[pcap_rmtauth](1)
    def apply(`type` : CInt, username : CString, password : CString)(using Zone): Ptr[pcap_rmtauth] = 
      val ____ptr = apply()
      (!____ptr).`type` = `type`
      (!____ptr).username = username
      (!____ptr).password = password
      ____ptr
    extension (struct: pcap_rmtauth)
      def `type` : CInt = struct._1
      def type_=(value: CInt): Unit = !struct.at1 = value
      def username : CString = struct._2
      def username_=(value: CString): Unit = !struct.at2 = value
      def password : CString = struct._3
      def password_=(value: CString): Unit = !struct.at3 = value

  opaque type pcap_samp = CStruct2[CInt, CInt]
  object pcap_samp:
    given _tag: Tag[pcap_samp] = Tag.materializeCStruct2Tag[CInt, CInt]
    def apply()(using Zone): Ptr[pcap_samp] = scala.scalanative.unsafe.alloc[pcap_samp](1)
    def apply(method : CInt, value : CInt)(using Zone): Ptr[pcap_samp] = 
      val ____ptr = apply()
      (!____ptr).method = method
      (!____ptr).value = value
      ____ptr
    extension (struct: pcap_samp)
      def method : CInt = struct._1
      def method_=(value: CInt): Unit = !struct.at1 = value
      def value : CInt = struct._2
      def value_=(value: CInt): Unit = !struct.at2 = value

  opaque type pcap_stat = CStruct3[u_int, u_int, u_int]
  object pcap_stat:
    given _tag: Tag[pcap_stat] = Tag.materializeCStruct3Tag[u_int, u_int, u_int]
    def apply()(using Zone): Ptr[pcap_stat] = scala.scalanative.unsafe.alloc[pcap_stat](1)
    def apply(ps_recv : u_int, ps_drop : u_int, ps_ifdrop : u_int)(using Zone): Ptr[pcap_stat] = 
      val ____ptr = apply()
      (!____ptr).ps_recv = ps_recv
      (!____ptr).ps_drop = ps_drop
      (!____ptr).ps_ifdrop = ps_ifdrop
      ____ptr
    extension (struct: pcap_stat)
      def ps_recv : u_int = struct._1
      def ps_recv_=(value: u_int): Unit = !struct.at1 = value
      def ps_drop : u_int = struct._2
      def ps_drop_=(value: u_int): Unit = !struct.at2 = value
      def ps_ifdrop : u_int = struct._3
      def ps_ifdrop_=(value: u_int): Unit = !struct.at3 = value

  opaque type pcap_t = CStruct0
  object pcap_t:
    given _tag: Tag[pcap_t] = Tag.materializeCStruct0Tag

  opaque type protoent = CStruct3[CString, Ptr[CString], CInt]
  object protoent:
    given _tag: Tag[protoent] = Tag.materializeCStruct3Tag[CString, Ptr[CString], CInt]
    def apply()(using Zone): Ptr[protoent] = scala.scalanative.unsafe.alloc[protoent](1)
    def apply(p_name : CString, p_aliases : Ptr[CString], p_proto : CInt)(using Zone): Ptr[protoent] = 
      val ____ptr = apply()
      (!____ptr).p_name = p_name
      (!____ptr).p_aliases = p_aliases
      (!____ptr).p_proto = p_proto
      ____ptr
    extension (struct: protoent)
      def p_name : CString = struct._1
      def p_name_=(value: CString): Unit = !struct.at1 = value
      def p_aliases : Ptr[CString] = struct._2
      def p_aliases_=(value: Ptr[CString]): Unit = !struct.at2 = value
      def p_proto : CInt = struct._3
      def p_proto_=(value: CInt): Unit = !struct.at3 = value

  opaque type rpcent = CStruct3[CString, Ptr[CString], CInt]
  object rpcent:
    given _tag: Tag[rpcent] = Tag.materializeCStruct3Tag[CString, Ptr[CString], CInt]
    def apply()(using Zone): Ptr[rpcent] = scala.scalanative.unsafe.alloc[rpcent](1)
    def apply(r_name : CString, r_aliases : Ptr[CString], r_number : CInt)(using Zone): Ptr[rpcent] = 
      val ____ptr = apply()
      (!____ptr).r_name = r_name
      (!____ptr).r_aliases = r_aliases
      (!____ptr).r_number = r_number
      ____ptr
    extension (struct: rpcent)
      def r_name : CString = struct._1
      def r_name_=(value: CString): Unit = !struct.at1 = value
      def r_aliases : Ptr[CString] = struct._2
      def r_aliases_=(value: Ptr[CString]): Unit = !struct.at2 = value
      def r_number : CInt = struct._3
      def r_number_=(value: CInt): Unit = !struct.at3 = value

  opaque type sa_endpoints = CStruct5[CUnsignedInt, Ptr[sockaddr], socklen_t, Ptr[sockaddr], socklen_t]
  object sa_endpoints:
    opaque type Struct0 = CStruct0
    object Struct0:
      given _tag: Tag[Struct0] = Tag.materializeCStruct0Tag
    given _tag: Tag[sa_endpoints] = Tag.materializeCStruct5Tag[CUnsignedInt, Ptr[sockaddr], socklen_t, Ptr[sockaddr], socklen_t]
    def apply()(using Zone): Ptr[sa_endpoints] = scala.scalanative.unsafe.alloc[sa_endpoints](1)
    def apply(sae_srcif : CUnsignedInt, sae_srcaddr : Ptr[sockaddr], sae_srcaddrlen : socklen_t, sae_dstaddr : Ptr[sockaddr], sae_dstaddrlen : socklen_t)(using Zone): Ptr[sa_endpoints] = 
      val ____ptr = apply()
      (!____ptr).sae_srcif = sae_srcif
      (!____ptr).sae_srcaddr = sae_srcaddr
      (!____ptr).sae_srcaddrlen = sae_srcaddrlen
      (!____ptr).sae_dstaddr = sae_dstaddr
      (!____ptr).sae_dstaddrlen = sae_dstaddrlen
      ____ptr
    extension (struct: sa_endpoints)
      def sae_srcif : CUnsignedInt = struct._1
      def sae_srcif_=(value: CUnsignedInt): Unit = !struct.at1 = value
      def sae_srcaddr : Ptr[sockaddr] = struct._2
      def sae_srcaddr_=(value: Ptr[sockaddr]): Unit = !struct.at2 = value
      def sae_srcaddrlen : socklen_t = struct._3
      def sae_srcaddrlen_=(value: socklen_t): Unit = !struct.at3 = value
      def sae_dstaddr : Ptr[sockaddr] = struct._4
      def sae_dstaddr_=(value: Ptr[sockaddr]): Unit = !struct.at4 = value
      def sae_dstaddrlen : socklen_t = struct._5
      def sae_dstaddrlen_=(value: socklen_t): Unit = !struct.at5 = value

  opaque type sa_endpoints_t = CStruct5[CUnsignedInt, Ptr[sockaddr], socklen_t, Ptr[sockaddr], socklen_t]
  object sa_endpoints_t:
    opaque type Struct0 = CStruct0
    object Struct0:
      given _tag: Tag[Struct0] = Tag.materializeCStruct0Tag
    given _tag: Tag[sa_endpoints_t] = Tag.materializeCStruct5Tag[CUnsignedInt, Ptr[sockaddr], socklen_t, Ptr[sockaddr], socklen_t]
    def apply()(using Zone): Ptr[sa_endpoints_t] = scala.scalanative.unsafe.alloc[sa_endpoints_t](1)
    def apply(sae_srcif : CUnsignedInt, sae_srcaddr : Ptr[sockaddr], sae_srcaddrlen : socklen_t, sae_dstaddr : Ptr[sockaddr], sae_dstaddrlen : socklen_t)(using Zone): Ptr[sa_endpoints_t] = 
      val ____ptr = apply()
      (!____ptr).sae_srcif = sae_srcif
      (!____ptr).sae_srcaddr = sae_srcaddr
      (!____ptr).sae_srcaddrlen = sae_srcaddrlen
      (!____ptr).sae_dstaddr = sae_dstaddr
      (!____ptr).sae_dstaddrlen = sae_dstaddrlen
      ____ptr
    extension (struct: sa_endpoints_t)
      def sae_srcif : CUnsignedInt = struct._1
      def sae_srcif_=(value: CUnsignedInt): Unit = !struct.at1 = value
      def sae_srcaddr : Ptr[sockaddr] = struct._2
      def sae_srcaddr_=(value: Ptr[sockaddr]): Unit = !struct.at2 = value
      def sae_srcaddrlen : socklen_t = struct._3
      def sae_srcaddrlen_=(value: socklen_t): Unit = !struct.at3 = value
      def sae_dstaddr : Ptr[sockaddr] = struct._4
      def sae_dstaddr_=(value: Ptr[sockaddr]): Unit = !struct.at4 = value
      def sae_dstaddrlen : socklen_t = struct._5
      def sae_dstaddrlen_=(value: socklen_t): Unit = !struct.at5 = value

  opaque type servent = CStruct4[CString, Ptr[CString], CInt, CString]
  object servent:
    given _tag: Tag[servent] = Tag.materializeCStruct4Tag[CString, Ptr[CString], CInt, CString]
    def apply()(using Zone): Ptr[servent] = scala.scalanative.unsafe.alloc[servent](1)
    def apply(s_name : CString, s_aliases : Ptr[CString], s_port : CInt, s_proto : CString)(using Zone): Ptr[servent] = 
      val ____ptr = apply()
      (!____ptr).s_name = s_name
      (!____ptr).s_aliases = s_aliases
      (!____ptr).s_port = s_port
      (!____ptr).s_proto = s_proto
      ____ptr
    extension (struct: servent)
      def s_name : CString = struct._1
      def s_name_=(value: CString): Unit = !struct.at1 = value
      def s_aliases : Ptr[CString] = struct._2
      def s_aliases_=(value: Ptr[CString]): Unit = !struct.at2 = value
      def s_port : CInt = struct._3
      def s_port_=(value: CInt): Unit = !struct.at3 = value
      def s_proto : CString = struct._4
      def s_proto_=(value: CString): Unit = !struct.at4 = value

  opaque type sf_hdtr = CStruct4[Ptr[iovec], CInt, Ptr[iovec], CInt]
  object sf_hdtr:
    given _tag: Tag[sf_hdtr] = Tag.materializeCStruct4Tag[Ptr[iovec], CInt, Ptr[iovec], CInt]
    def apply()(using Zone): Ptr[sf_hdtr] = scala.scalanative.unsafe.alloc[sf_hdtr](1)
    def apply(headers : Ptr[iovec], hdr_cnt : CInt, trailers : Ptr[iovec], trl_cnt : CInt)(using Zone): Ptr[sf_hdtr] = 
      val ____ptr = apply()
      (!____ptr).headers = headers
      (!____ptr).hdr_cnt = hdr_cnt
      (!____ptr).trailers = trailers
      (!____ptr).trl_cnt = trl_cnt
      ____ptr
    extension (struct: sf_hdtr)
      def headers : Ptr[iovec] = struct._1
      def headers_=(value: Ptr[iovec]): Unit = !struct.at1 = value
      def hdr_cnt : CInt = struct._2
      def hdr_cnt_=(value: CInt): Unit = !struct.at2 = value
      def trailers : Ptr[iovec] = struct._3
      def trailers_=(value: Ptr[iovec]): Unit = !struct.at3 = value
      def trl_cnt : CInt = struct._4
      def trl_cnt_=(value: CInt): Unit = !struct.at4 = value

  opaque type so_np_extensions = CStruct2[u_int32_t, u_int32_t]
  object so_np_extensions:
    given _tag: Tag[so_np_extensions] = Tag.materializeCStruct2Tag[u_int32_t, u_int32_t]
    def apply()(using Zone): Ptr[so_np_extensions] = scala.scalanative.unsafe.alloc[so_np_extensions](1)
    def apply(npx_flags : u_int32_t, npx_mask : u_int32_t)(using Zone): Ptr[so_np_extensions] = 
      val ____ptr = apply()
      (!____ptr).npx_flags = npx_flags
      (!____ptr).npx_mask = npx_mask
      ____ptr
    extension (struct: so_np_extensions)
      def npx_flags : u_int32_t = struct._1
      def npx_flags_=(value: u_int32_t): Unit = !struct.at1 = value
      def npx_mask : u_int32_t = struct._2
      def npx_mask_=(value: u_int32_t): Unit = !struct.at2 = value

  opaque type sockaddr = CStruct0
  object sockaddr:
    given _tag: Tag[sockaddr] = Tag.materializeCStruct0Tag

  opaque type sockaddr_in = CStruct5[__uint8_t, sa_family_t, in_port_t, in_addr, CArray[CChar, Nat._8]]
  object sockaddr_in:
    given _tag: Tag[sockaddr_in] = Tag.materializeCStruct5Tag[__uint8_t, sa_family_t, in_port_t, in_addr, CArray[CChar, Nat._8]]
    def apply()(using Zone): Ptr[sockaddr_in] = scala.scalanative.unsafe.alloc[sockaddr_in](1)
    def apply(sin_len : __uint8_t, sin_family : sa_family_t, sin_port : in_port_t, sin_addr : in_addr, sin_zero : CArray[CChar, Nat._8])(using Zone): Ptr[sockaddr_in] = 
      val ____ptr = apply()
      (!____ptr).sin_len = sin_len
      (!____ptr).sin_family = sin_family
      (!____ptr).sin_port = sin_port
      (!____ptr).sin_addr = sin_addr
      (!____ptr).sin_zero = sin_zero
      ____ptr
    extension (struct: sockaddr_in)
      def sin_len : __uint8_t = struct._1
      def sin_len_=(value: __uint8_t): Unit = !struct.at1 = value
      def sin_family : sa_family_t = struct._2
      def sin_family_=(value: sa_family_t): Unit = !struct.at2 = value
      def sin_port : in_port_t = struct._3
      def sin_port_=(value: in_port_t): Unit = !struct.at3 = value
      def sin_addr : in_addr = struct._4
      def sin_addr_=(value: in_addr): Unit = !struct.at4 = value
      def sin_zero : CArray[CChar, Nat._8] = struct._5
      def sin_zero_=(value: CArray[CChar, Nat._8]): Unit = !struct.at5 = value

  opaque type sockaddr_in6 = CStruct6[__uint8_t, sa_family_t, in_port_t, __uint32_t, in6_addr, __uint32_t]
  object sockaddr_in6:
    given _tag: Tag[sockaddr_in6] = Tag.materializeCStruct6Tag[__uint8_t, sa_family_t, in_port_t, __uint32_t, in6_addr, __uint32_t]
    def apply()(using Zone): Ptr[sockaddr_in6] = scala.scalanative.unsafe.alloc[sockaddr_in6](1)
    def apply(sin6_len : __uint8_t, sin6_family : sa_family_t, sin6_port : in_port_t, sin6_flowinfo : __uint32_t, sin6_addr : in6_addr, sin6_scope_id : __uint32_t)(using Zone): Ptr[sockaddr_in6] = 
      val ____ptr = apply()
      (!____ptr).sin6_len = sin6_len
      (!____ptr).sin6_family = sin6_family
      (!____ptr).sin6_port = sin6_port
      (!____ptr).sin6_flowinfo = sin6_flowinfo
      (!____ptr).sin6_addr = sin6_addr
      (!____ptr).sin6_scope_id = sin6_scope_id
      ____ptr
    extension (struct: sockaddr_in6)
      def sin6_len : __uint8_t = struct._1
      def sin6_len_=(value: __uint8_t): Unit = !struct.at1 = value
      def sin6_family : sa_family_t = struct._2
      def sin6_family_=(value: sa_family_t): Unit = !struct.at2 = value
      def sin6_port : in_port_t = struct._3
      def sin6_port_=(value: in_port_t): Unit = !struct.at3 = value
      def sin6_flowinfo : __uint32_t = struct._4
      def sin6_flowinfo_=(value: __uint32_t): Unit = !struct.at4 = value
      def sin6_addr : in6_addr = struct._5
      def sin6_addr_=(value: in6_addr): Unit = !struct.at5 = value
      def sin6_scope_id : __uint32_t = struct._6
      def sin6_scope_id_=(value: __uint32_t): Unit = !struct.at6 = value

  opaque type sockaddr_storage = CStruct5[__uint8_t, sa_family_t, CArray[CChar, Nat._6], __int64_t, CArray[CChar, Nat.Digit3[Nat._1, Nat._1, Nat._2]]]
  object sockaddr_storage:
    given _tag: Tag[sockaddr_storage] = Tag.materializeCStruct5Tag[__uint8_t, sa_family_t, CArray[CChar, Nat._6], __int64_t, CArray[CChar, Nat.Digit3[Nat._1, Nat._1, Nat._2]]]
    def apply()(using Zone): Ptr[sockaddr_storage] = scala.scalanative.unsafe.alloc[sockaddr_storage](1)
    def apply(ss_len : __uint8_t, ss_family : sa_family_t, __ss_pad1 : CArray[CChar, Nat._6], __ss_align : __int64_t, __ss_pad2 : CArray[CChar, Nat.Digit3[Nat._1, Nat._1, Nat._2]])(using Zone): Ptr[sockaddr_storage] = 
      val ____ptr = apply()
      (!____ptr).ss_len = ss_len
      (!____ptr).ss_family = ss_family
      (!____ptr).__ss_pad1 = __ss_pad1
      (!____ptr).__ss_align = __ss_align
      (!____ptr).__ss_pad2 = __ss_pad2
      ____ptr
    extension (struct: sockaddr_storage)
      def ss_len : __uint8_t = struct._1
      def ss_len_=(value: __uint8_t): Unit = !struct.at1 = value
      def ss_family : sa_family_t = struct._2
      def ss_family_=(value: sa_family_t): Unit = !struct.at2 = value
      def __ss_pad1 : CArray[CChar, Nat._6] = struct._3
      def __ss_pad1_=(value: CArray[CChar, Nat._6]): Unit = !struct.at3 = value
      def __ss_align : __int64_t = struct._4
      def __ss_align_=(value: __int64_t): Unit = !struct.at4 = value
      def __ss_pad2 : CArray[CChar, Nat.Digit3[Nat._1, Nat._1, Nat._2]] = struct._5
      def __ss_pad2_=(value: CArray[CChar, Nat.Digit3[Nat._1, Nat._1, Nat._2]]): Unit = !struct.at5 = value

  opaque type sockproto = CStruct2[__uint16_t, __uint16_t]
  object sockproto:
    given _tag: Tag[sockproto] = Tag.materializeCStruct2Tag[__uint16_t, __uint16_t]
    def apply()(using Zone): Ptr[sockproto] = scala.scalanative.unsafe.alloc[sockproto](1)
    def apply(sp_family : __uint16_t, sp_protocol : __uint16_t)(using Zone): Ptr[sockproto] = 
      val ____ptr = apply()
      (!____ptr).sp_family = sp_family
      (!____ptr).sp_protocol = sp_protocol
      ____ptr
    extension (struct: sockproto)
      def sp_family : __uint16_t = struct._1
      def sp_family_=(value: __uint16_t): Unit = !struct.at1 = value
      def sp_protocol : __uint16_t = struct._2
      def sp_protocol_=(value: __uint16_t): Unit = !struct.at2 = value

  opaque type timespec = CStruct2[__darwin_time_t, CLongInt]
  object timespec:
    given _tag: Tag[timespec] = Tag.materializeCStruct2Tag[__darwin_time_t, CLongInt]
    def apply()(using Zone): Ptr[timespec] = scala.scalanative.unsafe.alloc[timespec](1)
    def apply(tv_sec : __darwin_time_t, tv_nsec : CLongInt)(using Zone): Ptr[timespec] = 
      val ____ptr = apply()
      (!____ptr).tv_sec = tv_sec
      (!____ptr).tv_nsec = tv_nsec
      ____ptr
    extension (struct: timespec)
      def tv_sec : __darwin_time_t = struct._1
      def tv_sec_=(value: __darwin_time_t): Unit = !struct.at1 = value
      def tv_nsec : CLongInt = struct._2
      def tv_nsec_=(value: CLongInt): Unit = !struct.at2 = value

  opaque type timeval = CStruct2[__darwin_time_t, __darwin_suseconds_t]
  object timeval:
    given _tag: Tag[timeval] = Tag.materializeCStruct2Tag[__darwin_time_t, __darwin_suseconds_t]
    def apply()(using Zone): Ptr[timeval] = scala.scalanative.unsafe.alloc[timeval](1)
    def apply(tv_sec : __darwin_time_t, tv_usec : __darwin_suseconds_t)(using Zone): Ptr[timeval] = 
      val ____ptr = apply()
      (!____ptr).tv_sec = tv_sec
      (!____ptr).tv_usec = tv_usec
      ____ptr
    extension (struct: timeval)
      def tv_sec : __darwin_time_t = struct._1
      def tv_sec_=(value: __darwin_time_t): Unit = !struct.at1 = value
      def tv_usec : __darwin_suseconds_t = struct._2
      def tv_usec_=(value: __darwin_suseconds_t): Unit = !struct.at2 = value

  opaque type timeval64 = CStruct2[__int64_t, __int64_t]
  object timeval64:
    given _tag: Tag[timeval64] = Tag.materializeCStruct2Tag[__int64_t, __int64_t]
    def apply()(using Zone): Ptr[timeval64] = scala.scalanative.unsafe.alloc[timeval64](1)
    def apply(tv_sec : __int64_t, tv_usec : __int64_t)(using Zone): Ptr[timeval64] = 
      val ____ptr = apply()
      (!____ptr).tv_sec = tv_sec
      (!____ptr).tv_usec = tv_usec
      ____ptr
    extension (struct: timeval64)
      def tv_sec : __int64_t = struct._1
      def tv_sec_=(value: __int64_t): Unit = !struct.at1 = value
      def tv_usec : __int64_t = struct._2
      def tv_usec_=(value: __int64_t): Unit = !struct.at2 = value

  opaque type timezone = CStruct2[CInt, CInt]
  object timezone:
    given _tag: Tag[timezone] = Tag.materializeCStruct2Tag[CInt, CInt]
    def apply()(using Zone): Ptr[timezone] = scala.scalanative.unsafe.alloc[timezone](1)
    def apply(tz_minuteswest : CInt, tz_dsttime : CInt)(using Zone): Ptr[timezone] = 
      val ____ptr = apply()
      (!____ptr).tz_minuteswest = tz_minuteswest
      (!____ptr).tz_dsttime = tz_dsttime
      ____ptr
    extension (struct: timezone)
      def tz_minuteswest : CInt = struct._1
      def tz_minuteswest_=(value: CInt): Unit = !struct.at1 = value
      def tz_dsttime : CInt = struct._2
      def tz_dsttime_=(value: CInt): Unit = !struct.at2 = value

  opaque type tm = CStruct11[CInt, CInt, CInt, CInt, CInt, CInt, CInt, CInt, CInt, CLongInt, CString]
  object tm:
    given _tag: Tag[tm] = Tag.materializeCStruct11Tag[CInt, CInt, CInt, CInt, CInt, CInt, CInt, CInt, CInt, CLongInt, CString]
    def apply()(using Zone): Ptr[tm] = scala.scalanative.unsafe.alloc[tm](1)
    def apply(tm_sec : CInt, tm_min : CInt, tm_hour : CInt, tm_mday : CInt, tm_mon : CInt, tm_year : CInt, tm_wday : CInt, tm_yday : CInt, tm_isdst : CInt, tm_gmtoff : CLongInt, tm_zone : CString)(using Zone): Ptr[tm] = 
      val ____ptr = apply()
      (!____ptr).tm_sec = tm_sec
      (!____ptr).tm_min = tm_min
      (!____ptr).tm_hour = tm_hour
      (!____ptr).tm_mday = tm_mday
      (!____ptr).tm_mon = tm_mon
      (!____ptr).tm_year = tm_year
      (!____ptr).tm_wday = tm_wday
      (!____ptr).tm_yday = tm_yday
      (!____ptr).tm_isdst = tm_isdst
      (!____ptr).tm_gmtoff = tm_gmtoff
      (!____ptr).tm_zone = tm_zone
      ____ptr
    extension (struct: tm)
      def tm_sec : CInt = struct._1
      def tm_sec_=(value: CInt): Unit = !struct.at1 = value
      def tm_min : CInt = struct._2
      def tm_min_=(value: CInt): Unit = !struct.at2 = value
      def tm_hour : CInt = struct._3
      def tm_hour_=(value: CInt): Unit = !struct.at3 = value
      def tm_mday : CInt = struct._4
      def tm_mday_=(value: CInt): Unit = !struct.at4 = value
      def tm_mon : CInt = struct._5
      def tm_mon_=(value: CInt): Unit = !struct.at5 = value
      def tm_year : CInt = struct._6
      def tm_year_=(value: CInt): Unit = !struct.at6 = value
      def tm_wday : CInt = struct._7
      def tm_wday_=(value: CInt): Unit = !struct.at7 = value
      def tm_yday : CInt = struct._8
      def tm_yday_=(value: CInt): Unit = !struct.at8 = value
      def tm_isdst : CInt = struct._9
      def tm_isdst_=(value: CInt): Unit = !struct.at9 = value
      def tm_gmtoff : CLongInt = struct._10
      def tm_gmtoff_=(value: CLongInt): Unit = !struct.at10 = value
      def tm_zone : CString = struct._11
      def tm_zone_=(value: CString): Unit = !struct.at11 = value

@extern
private[libpcap] object extern_functions:
  import types.*
  def _OSSwapInt16(_data : __uint16_t): __uint16_t = extern

  def _OSSwapInt32(_data : __uint32_t): __uint32_t = extern

  def _OSSwapInt64(_data : __uint64_t): __uint64_t = extern

  def __darwin_check_fd_set(_a : CInt, _b : Ptr[Byte]): CInt = extern

  def __darwin_check_fd_set_overflow(_0 : CInt, _1 : Ptr[Byte], _2 : CInt): CInt = extern

  def __darwin_fd_clr(_fd : CInt, _p : Ptr[fd_set]): Unit = extern

  def __darwin_fd_isset(_fd : CInt, _p : Ptr[fd_set]): CInt = extern

  def __darwin_fd_set(_fd : CInt, _p : Ptr[fd_set]): Unit = extern

  private[libpcap] def __sn_wrap_libpcap_getipv4sourcefilter(_0 : CInt, _1 : Ptr[in_addr], _2 : Ptr[in_addr], _3 : Ptr[uint32_t], _4 : Ptr[uint32_t], _5 : Ptr[in_addr]): CInt = extern

  private[libpcap] def __sn_wrap_libpcap_inet_lnaof(_0 : Ptr[in_addr]): in_addr_t = extern

  private[libpcap] def __sn_wrap_libpcap_inet_makeaddr(_0 : in_addr_t, _1 : in_addr_t, __return : Ptr[in_addr]): Unit = extern

  private[libpcap] def __sn_wrap_libpcap_inet_netof(_0 : Ptr[in_addr]): in_addr_t = extern

  private[libpcap] def __sn_wrap_libpcap_inet_ntoa(_0 : Ptr[in_addr]): CString = extern

  private[libpcap] def __sn_wrap_libpcap_setipv4sourcefilter(_0 : CInt, _1 : Ptr[in_addr], _2 : Ptr[in_addr], _3 : uint32_t, _4 : uint32_t, _5 : Ptr[in_addr]): CInt = extern

  def accept(_0 : CInt, _1 : Ptr[sockaddr], _2 : Ptr[socklen_t]): CInt = extern

  def addr2ascii(_0 : CInt, _1 : Ptr[Byte], _2 : CInt, _3 : CString): CString = extern

  def adjtime(_0 : Ptr[timeval], _1 : Ptr[timeval]): CInt = extern

  def ascii2addr(_0 : CInt, _1 : CString, _2 : Ptr[Byte]): CInt = extern

  def asctime(_0 : Ptr[tm]): CString = extern

  def asctime_r(_0 : Ptr[tm], _1 : CString): CString = extern

  def bind(_0 : CInt, _1 : Ptr[sockaddr], _2 : socklen_t): CInt = extern

  def bindresvport(_0 : CInt, _1 : Ptr[sockaddr_in]): CInt = extern

  def bindresvport_sa(_0 : CInt, _1 : Ptr[sockaddr]): CInt = extern

  def bpf_dump(_0 : Ptr[bpf_program], _1 : CInt): Unit = extern

  def bpf_filter(_0 : Ptr[bpf_insn], _1 : Ptr[u_char], _2 : u_int, _3 : u_int): u_int = extern

  def bpf_image(_0 : Ptr[bpf_insn], _1 : CInt): CString = extern

  def bpf_validate(f : Ptr[bpf_insn], len : CInt): CInt = extern

  def clock(): clock_t = extern

  def clock_getres(__clock_id : clockid_t, __res : Ptr[timespec]): CInt = extern

  def clock_gettime(__clock_id : clockid_t, __tp : Ptr[timespec]): CInt = extern

  def clock_gettime_nsec_np(__clock_id : clockid_t): __uint64_t = extern

  def clock_settime(__clock_id : clockid_t, __tp : Ptr[timespec]): CInt = extern

  def connect(_0 : CInt, _1 : Ptr[sockaddr], _2 : socklen_t): CInt = extern

  def connectx(_0 : CInt, _1 : Ptr[sa_endpoints_t], _2 : sae_associd_t, _3 : CUnsignedInt, _4 : Ptr[iovec], _5 : CUnsignedInt, _6 : Ptr[size_t], _7 : Ptr[sae_connid_t]): CInt = extern

  def ctime(_0 : Ptr[time_t]): CString = extern

  def ctime_r(_0 : Ptr[time_t], _1 : CString): CString = extern

  def difftime(_0 : time_t, _1 : time_t): Double = extern

  def disconnectx(_0 : CInt, _1 : sae_associd_t, _2 : sae_connid_t): CInt = extern

  def endhostent(): Unit = extern

  def endnetent(): Unit = extern

  def endnetgrent(): Unit = extern

  def endprotoent(): Unit = extern

  def endrpcent(): Unit = extern

  def endservent(): Unit = extern

  def freeaddrinfo(_0 : Ptr[addrinfo]): Unit = extern

  def freehostent(_0 : Ptr[hostent]): Unit = extern

  def futimes(_0 : CInt, _1 : Ptr[timeval]): CInt = extern

  def gai_strerror(_0 : CInt): CString = extern

  def getaddrinfo(_0 : CString, _1 : CString, _2 : Ptr[addrinfo], _3 : Ptr[Ptr[addrinfo]]): CInt = extern

  def getdate(_0 : CString): Ptr[tm] = extern

  def gethostbyaddr(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt): Ptr[hostent] = extern

  def gethostbyname(_0 : CString): Ptr[hostent] = extern

  def gethostbyname2(_0 : CString, _1 : CInt): Ptr[hostent] = extern

  def gethostent(): Ptr[hostent] = extern

  def getipnodebyaddr(_0 : Ptr[Byte], _1 : size_t, _2 : CInt, _3 : Ptr[CInt]): Ptr[hostent] = extern

  def getipnodebyname(_0 : CString, _1 : CInt, _2 : CInt, _3 : Ptr[CInt]): Ptr[hostent] = extern

  def getitimer(_0 : CInt, _1 : Ptr[itimerval]): CInt = extern

  def getnameinfo(_0 : Ptr[sockaddr], _1 : socklen_t, _2 : CString, _3 : socklen_t, _4 : CString, _5 : socklen_t, _6 : CInt): CInt = extern

  def getnetbyaddr(_0 : uint32_t, _1 : CInt): Ptr[netent] = extern

  def getnetbyname(_0 : CString): Ptr[netent] = extern

  def getnetent(): Ptr[netent] = extern

  def getnetgrent(_0 : Ptr[CString], _1 : Ptr[CString], _2 : Ptr[CString]): CInt = extern

  def getpeername(_0 : CInt, _1 : Ptr[sockaddr], _2 : Ptr[socklen_t]): CInt = extern

  def getprotobyname(_0 : CString): Ptr[protoent] = extern

  def getprotobynumber(_0 : CInt): Ptr[protoent] = extern

  def getprotoent(): Ptr[protoent] = extern

  def getrpcbyname(name : CString): Ptr[rpcent] = extern

  def getrpcbynumber(number : CInt): Ptr[rpcent] = extern

  def getrpcent(): Ptr[rpcent] = extern

  def getservbyname(_0 : CString, _1 : CString): Ptr[servent] = extern

  def getservbyport(_0 : CInt, _1 : CString): Ptr[servent] = extern

  def getservent(): Ptr[servent] = extern

  def getsockname(_0 : CInt, _1 : Ptr[sockaddr], _2 : Ptr[socklen_t]): CInt = extern

  def getsockopt(_0 : CInt, _1 : CInt, _2 : CInt, _3 : Ptr[Byte], _4 : Ptr[socklen_t]): CInt = extern

  def getsourcefilter(_0 : CInt, _1 : uint32_t, _2 : Ptr[sockaddr], _3 : socklen_t, _4 : Ptr[uint32_t], _5 : Ptr[uint32_t], _6 : Ptr[sockaddr_storage]): CInt = extern

  def gettimeofday(_0 : Ptr[timeval], _1 : Ptr[Byte]): CInt = extern

  def gmtime(_0 : Ptr[time_t]): Ptr[tm] = extern

  def gmtime_r(_0 : Ptr[time_t], _1 : Ptr[tm]): Ptr[tm] = extern

  def herror(_0 : CString): Unit = extern

  def hstrerror(_0 : CInt): CString = extern

  def inet6_opt_append(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt, _3 : __uint8_t, _4 : socklen_t, _5 : __uint8_t, _6 : Ptr[Ptr[Byte]]): CInt = extern

  def inet6_opt_find(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt, _3 : __uint8_t, _4 : Ptr[socklen_t], _5 : Ptr[Ptr[Byte]]): CInt = extern

  def inet6_opt_finish(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt): CInt = extern

  def inet6_opt_get_val(_0 : Ptr[Byte], _1 : CInt, _2 : Ptr[Byte], _3 : socklen_t): CInt = extern

  def inet6_opt_init(_0 : Ptr[Byte], _1 : socklen_t): CInt = extern

  def inet6_opt_next(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt, _3 : Ptr[__uint8_t], _4 : Ptr[socklen_t], _5 : Ptr[Ptr[Byte]]): CInt = extern

  def inet6_opt_set_val(_0 : Ptr[Byte], _1 : CInt, _2 : Ptr[Byte], _3 : socklen_t): CInt = extern

  def inet6_option_alloc(_0 : Ptr[cmsghdr], _1 : CInt, _2 : CInt, _3 : CInt): Ptr[__uint8_t] = extern

  def inet6_option_append(_0 : Ptr[cmsghdr], _1 : Ptr[__uint8_t], _2 : CInt, _3 : CInt): CInt = extern

  def inet6_option_find(_0 : Ptr[cmsghdr], _1 : Ptr[Ptr[__uint8_t]], _2 : CInt): CInt = extern

  def inet6_option_init(_0 : Ptr[Byte], _1 : Ptr[Ptr[cmsghdr]], _2 : CInt): CInt = extern

  def inet6_option_next(_0 : Ptr[cmsghdr], _1 : Ptr[Ptr[__uint8_t]]): CInt = extern

  def inet6_option_space(_0 : CInt): CInt = extern

  def inet6_rth_add(_0 : Ptr[Byte], _1 : Ptr[in6_addr]): CInt = extern

  def inet6_rth_getaddr(_0 : Ptr[Byte], _1 : CInt): Ptr[in6_addr] = extern

  def inet6_rth_init(_0 : Ptr[Byte], _1 : socklen_t, _2 : CInt, _3 : CInt): Ptr[Byte] = extern

  def inet6_rth_reverse(_0 : Ptr[Byte], _1 : Ptr[Byte]): CInt = extern

  def inet6_rth_segments(_0 : Ptr[Byte]): CInt = extern

  def inet6_rth_space(_0 : CInt, _1 : CInt): socklen_t = extern

  def inet6_rthdr_add(_0 : Ptr[cmsghdr], _1 : Ptr[in6_addr], _2 : CUnsignedInt): CInt = extern

  def inet6_rthdr_getaddr(_0 : Ptr[cmsghdr], _1 : CInt): Ptr[in6_addr] = extern

  def inet6_rthdr_getflags(_0 : Ptr[cmsghdr], _1 : CInt): CInt = extern

  def inet6_rthdr_init(_0 : Ptr[Byte], _1 : CInt): Ptr[cmsghdr] = extern

  def inet6_rthdr_lasthop(_0 : Ptr[cmsghdr], _1 : CUnsignedInt): CInt = extern

  def inet6_rthdr_segments(_0 : Ptr[cmsghdr]): CInt = extern

  def inet6_rthdr_space(_0 : CInt, _1 : CInt): size_t = extern

  def inet_addr(_0 : CString): in_addr_t = extern

  def inet_aton(_0 : CString, _1 : Ptr[in_addr]): CInt = extern

  def inet_net_ntop(_0 : CInt, _1 : Ptr[Byte], _2 : CInt, _3 : CString, _4 : __darwin_size_t): CString = extern

  def inet_net_pton(_0 : CInt, _1 : CString, _2 : Ptr[Byte], _3 : __darwin_size_t): CInt = extern

  def inet_neta(_0 : in_addr_t, _1 : CString, _2 : __darwin_size_t): CString = extern

  def inet_network(_0 : CString): in_addr_t = extern

  def inet_nsap_addr(_0 : CString, _1 : Ptr[CUnsignedChar], _2 : CInt): CUnsignedInt = extern

  def inet_nsap_ntoa(_0 : CInt, _1 : Ptr[CUnsignedChar], _2 : CString): CString = extern

  def inet_ntop(_0 : CInt, _1 : Ptr[Byte], _2 : CString, _3 : socklen_t): CString = extern

  def inet_pton(_0 : CInt, _1 : CString, _2 : Ptr[Byte]): CInt = extern

  def innetgr(_0 : CString, _1 : CString, _2 : CString, _3 : CString): CInt = extern

  def listen(_0 : CInt, _1 : CInt): CInt = extern

  def localtime(_0 : Ptr[time_t]): Ptr[tm] = extern

  def localtime_r(_0 : Ptr[time_t], _1 : Ptr[tm]): Ptr[tm] = extern

  def lutimes(_0 : CString, _1 : Ptr[timeval]): CInt = extern

  def mktime(_0 : Ptr[tm]): time_t = extern

  def nanosleep(__rqtp : Ptr[timespec], __rmtp : Ptr[timespec]): CInt = extern

  def pcap_activate(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_apple_set_exthdr(p : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_breakloop(_0 : Ptr[pcap_t]): Unit = extern

  def pcap_bufsize(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_can_set_rfmon(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_close(_0 : Ptr[pcap_t]): Unit = extern

  def pcap_compile(_0 : Ptr[pcap_t], _1 : Ptr[bpf_program], _2 : CString, _3 : CInt, _4 : bpf_u_int32): CInt = extern

  def pcap_compile_nopcap(_0 : CInt, _1 : CInt, _2 : Ptr[bpf_program], _3 : CString, _4 : CInt, _5 : bpf_u_int32): CInt = extern

  def pcap_create(_0 : CString, _1 : CString): Ptr[pcap_t] = extern

  def pcap_createsrcstr(source : CString, `type` : CInt, host : CString, port : CString, name : CString, errbuf : CString): CInt = extern

  def pcap_datalink(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_datalink_ext(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_datalink_name_to_val(_0 : CString): CInt = extern

  def pcap_datalink_val_to_description(_0 : CInt): CString = extern

  def pcap_datalink_val_to_description_or_dlt(_0 : CInt): CString = extern

  def pcap_datalink_val_to_name(_0 : CInt): CString = extern

  def pcap_dispatch(_0 : Ptr[pcap_t], _1 : CInt, _2 : pcap_handler, _3 : Ptr[u_char]): CInt = extern

  def pcap_dump(_0 : Ptr[u_char], _1 : Ptr[pcap_pkthdr], _2 : Ptr[u_char]): Unit = extern

  def pcap_dump_close(_0 : Ptr[pcap_dumper_t]): Unit = extern

  def pcap_dump_file(_0 : Ptr[pcap_dumper_t]): Ptr[FILE] = extern

  def pcap_dump_flush(_0 : Ptr[pcap_dumper_t]): CInt = extern

  def pcap_dump_fopen(_0 : Ptr[pcap_t], fp : Ptr[FILE]): Ptr[pcap_dumper_t] = extern

  def pcap_dump_ftell(_0 : Ptr[pcap_dumper_t]): CLongInt = extern

  def pcap_dump_ftell64(_0 : Ptr[pcap_dumper_t]): int64_t = extern

  def pcap_dump_open(_0 : Ptr[pcap_t], _1 : CString): Ptr[pcap_dumper_t] = extern

  def pcap_dump_open_append(_0 : Ptr[pcap_t], _1 : CString): Ptr[pcap_dumper_t] = extern

  def pcap_file(_0 : Ptr[pcap_t]): Ptr[FILE] = extern

  def pcap_fileno(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_findalldevs(_0 : Ptr[Ptr[pcap_if_t]], _1 : CString): CInt = extern

  def pcap_findalldevs_ex(source : CString, auth : Ptr[pcap_rmtauth], alldevs : Ptr[Ptr[pcap_if_t]], errbuf : CString): CInt = extern

  def pcap_fopen_offline(_0 : Ptr[FILE], _1 : CString): Ptr[pcap_t] = extern

  def pcap_fopen_offline_with_tstamp_precision(_0 : Ptr[FILE], _1 : u_int, _2 : CString): Ptr[pcap_t] = extern

  def pcap_free_datalinks(_0 : Ptr[CInt]): Unit = extern

  def pcap_free_selectable_fd_list(_0 : Ptr[CInt]): Unit = extern

  def pcap_free_tstamp_types(_0 : Ptr[CInt]): Unit = extern

  def pcap_freealldevs(_0 : Ptr[pcap_if_t]): Unit = extern

  def pcap_freecode(_0 : Ptr[bpf_program]): Unit = extern

  def pcap_get_required_select_timeout(_0 : Ptr[pcap_t]): Ptr[timeval] = extern

  def pcap_get_selectable_fd(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_get_selectable_fd_list(_0 : Ptr[pcap_t], _1 : Ptr[Ptr[CInt]]): CInt = extern

  def pcap_get_tstamp_precision(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_geterr(_0 : Ptr[pcap_t]): CString = extern

  def pcap_getnonblock(_0 : Ptr[pcap_t], _1 : CString): CInt = extern

  def pcap_inject(_0 : Ptr[pcap_t], _1 : Ptr[Byte], _2 : size_t): CInt = extern

  def pcap_is_swapped(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_lib_version(): CString = extern

  def pcap_list_datalinks(_0 : Ptr[pcap_t], _1 : Ptr[Ptr[CInt]]): CInt = extern

  def pcap_list_tstamp_types(_0 : Ptr[pcap_t], _1 : Ptr[Ptr[CInt]]): CInt = extern

  def pcap_lookupdev(_0 : CString): CString = extern

  def pcap_lookupnet(_0 : CString, _1 : Ptr[bpf_u_int32], _2 : Ptr[bpf_u_int32], _3 : CString): CInt = extern

  def pcap_loop(_0 : Ptr[pcap_t], _1 : CInt, _2 : pcap_handler, _3 : Ptr[u_char]): CInt = extern

  def pcap_major_version(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_minor_version(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_next(_0 : Ptr[pcap_t], _1 : Ptr[pcap_pkthdr]): Ptr[u_char] = extern

  def pcap_next_ex(_0 : Ptr[pcap_t], _1 : Ptr[Ptr[pcap_pkthdr]], _2 : Ptr[Ptr[u_char]]): CInt = extern

  def pcap_offline_filter(_0 : Ptr[bpf_program], _1 : Ptr[pcap_pkthdr], _2 : Ptr[u_char]): CInt = extern

  def pcap_open(source : CString, snaplen : CInt, flags : CInt, read_timeout : CInt, auth : Ptr[pcap_rmtauth], errbuf : CString): Ptr[pcap_t] = extern

  def pcap_open_dead(_0 : CInt, _1 : CInt): Ptr[pcap_t] = extern

  def pcap_open_dead_with_tstamp_precision(_0 : CInt, _1 : CInt, _2 : u_int): Ptr[pcap_t] = extern

  def pcap_open_live(_0 : CString, _1 : CInt, _2 : CInt, _3 : CInt, _4 : CString): Ptr[pcap_t] = extern

  def pcap_open_offline(_0 : CString, _1 : CString): Ptr[pcap_t] = extern

  def pcap_open_offline_with_tstamp_precision(_0 : CString, _1 : u_int, _2 : CString): Ptr[pcap_t] = extern

  def pcap_parsesrcstr(source : CString, `type` : Ptr[CInt], host : CString, port : CString, name : CString, errbuf : CString): CInt = extern

  def pcap_perror(_0 : Ptr[pcap_t], _1 : CString): Unit = extern

  def pcap_remoteact_accept(address : CString, port : CString, hostlist : CString, connectinghost : CString, auth : Ptr[pcap_rmtauth], errbuf : CString): CInt = extern

  def pcap_remoteact_cleanup(): Unit = extern

  def pcap_remoteact_close(host : CString, errbuf : CString): CInt = extern

  def pcap_remoteact_list(hostlist : CString, sep : CChar, size : CInt, errbuf : CString): CInt = extern

  def pcap_sendpacket(_0 : Ptr[pcap_t], _1 : Ptr[u_char], _2 : CInt): CInt = extern

  def pcap_set_buffer_size(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_datalink(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_immediate_mode(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_promisc(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_rfmon(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_snaplen(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_timeout(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_tstamp_precision(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_set_tstamp_type(_0 : Ptr[pcap_t], _1 : CInt): CInt = extern

  def pcap_setdirection(_0 : Ptr[pcap_t], _1 : pcap_direction_t): CInt = extern

  def pcap_setfilter(_0 : Ptr[pcap_t], _1 : Ptr[bpf_program]): CInt = extern

  def pcap_setnonblock(_0 : Ptr[pcap_t], _1 : CInt, _2 : CString): CInt = extern

  def pcap_setsampling(p : Ptr[pcap_t]): Ptr[pcap_samp] = extern

  def pcap_snapshot(_0 : Ptr[pcap_t]): CInt = extern

  def pcap_stats(_0 : Ptr[pcap_t], _1 : Ptr[pcap_stat]): CInt = extern

  def pcap_statustostr(_0 : CInt): CString = extern

  def pcap_strerror(_0 : CInt): CString = extern

  def pcap_tstamp_type_name_to_val(_0 : CString): CInt = extern

  def pcap_tstamp_type_val_to_description(_0 : CInt): CString = extern

  def pcap_tstamp_type_val_to_name(_0 : CInt): CString = extern

  def pfctlinput(_0 : CInt, _1 : Ptr[sockaddr]): Unit = extern

  def posix2time(_0 : time_t): time_t = extern

  def recv(_0 : CInt, _1 : Ptr[Byte], _2 : size_t, _3 : CInt): ssize_t = extern

  def recvfrom(_0 : CInt, _1 : Ptr[Byte], _2 : size_t, _3 : CInt, _4 : Ptr[sockaddr], _5 : Ptr[socklen_t]): ssize_t = extern

  def recvmsg(_0 : CInt, _1 : Ptr[msghdr], _2 : CInt): ssize_t = extern

  def select(_0 : CInt, _1 : Ptr[fd_set], _2 : Ptr[fd_set], _3 : Ptr[fd_set], _4 : Ptr[timeval]): CInt = extern

  def send(_0 : CInt, _1 : Ptr[Byte], _2 : size_t, _3 : CInt): ssize_t = extern

  def sendfile(_0 : CInt, _1 : CInt, _2 : off_t, _3 : Ptr[off_t], _4 : Ptr[sf_hdtr], _5 : CInt): CInt = extern

  def sendmsg(_0 : CInt, _1 : Ptr[msghdr], _2 : CInt): ssize_t = extern

  def sendto(_0 : CInt, _1 : Ptr[Byte], _2 : size_t, _3 : CInt, _4 : Ptr[sockaddr], _5 : socklen_t): ssize_t = extern

  def sethostent(_0 : CInt): Unit = extern

  def setitimer(_0 : CInt, _1 : Ptr[itimerval], _2 : Ptr[itimerval]): CInt = extern

  def setnetent(_0 : CInt): Unit = extern

  def setnetgrent(_0 : CString): Unit = extern

  def setprotoent(_0 : CInt): Unit = extern

  def setrpcent(stayopen : CInt): Unit = extern

  def setservent(_0 : CInt): Unit = extern

  def setsockopt(_0 : CInt, _1 : CInt, _2 : CInt, _3 : Ptr[Byte], _4 : socklen_t): CInt = extern

  def setsourcefilter(_0 : CInt, _1 : uint32_t, _2 : Ptr[sockaddr], _3 : socklen_t, _4 : uint32_t, _5 : uint32_t, _6 : Ptr[sockaddr_storage]): CInt = extern

  def settimeofday(_0 : Ptr[timeval], _1 : Ptr[timezone]): CInt = extern

  def shutdown(_0 : CInt, _1 : CInt): CInt = extern

  def sockatmark(_0 : CInt): CInt = extern

  def socket(_0 : CInt, _1 : CInt, _2 : CInt): CInt = extern

  def socketpair(_0 : CInt, _1 : CInt, _2 : CInt, _3 : Ptr[CInt]): CInt = extern

  def strftime(_0 : CString, _1 : size_t, _2 : CString, _3 : Ptr[tm]): size_t = extern

  def strptime(_0 : CString, _1 : CString, _2 : Ptr[tm]): CString = extern

  def time(_0 : Ptr[time_t]): time_t = extern

  def time2posix(_0 : time_t): time_t = extern

  def timegm(_0 : Ptr[tm]): time_t = extern

  def timelocal(_0 : Ptr[tm]): time_t = extern

  def timespec_get(ts : Ptr[timespec], base : CInt): CInt = extern

  def tzset(): Unit = extern

  def tzsetwall(): Unit = extern

  def utimes(_0 : CString, _1 : Ptr[timeval]): CInt = extern

object functions:
  import types.*
  import extern_functions.*
  export extern_functions.*
  def getipv4sourcefilter(_0 : CInt, _1 : in_addr, _2 : in_addr, _3 : Ptr[uint32_t], _4 : Ptr[uint32_t], _5 : Ptr[in_addr])(using Zone): CInt = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](2)
    !(__ptr_0 + 0) = _1
    !(__ptr_0 + 1) = _2
    __sn_wrap_libpcap_getipv4sourcefilter(_0, (__ptr_0 + 0), (__ptr_0 + 1), _3, _4, _5)

  def getipv4sourcefilter(_0 : CInt, _1 : Ptr[in_addr], _2 : Ptr[in_addr], _3 : Ptr[uint32_t], _4 : Ptr[uint32_t], _5 : Ptr[in_addr]): CInt = 
    __sn_wrap_libpcap_getipv4sourcefilter(_0, _1, _2, _3, _4, _5)

  def inet_lnaof(_0 : in_addr)(using Zone): in_addr_t = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](1)
    !(__ptr_0 + 0) = _0
    __sn_wrap_libpcap_inet_lnaof((__ptr_0 + 0))

  def inet_lnaof(_0 : Ptr[in_addr]): in_addr_t = 
    __sn_wrap_libpcap_inet_lnaof(_0)

  def inet_makeaddr(_0 : in_addr_t, _1 : in_addr_t)(using Zone): in_addr = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](1)
    __sn_wrap_libpcap_inet_makeaddr(_0, _1, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  def inet_makeaddr(_0 : in_addr_t, _1 : in_addr_t)(__return : Ptr[in_addr]): Unit = 
    __sn_wrap_libpcap_inet_makeaddr(_0, _1, __return)

  def inet_netof(_0 : in_addr)(using Zone): in_addr_t = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](1)
    !(__ptr_0 + 0) = _0
    __sn_wrap_libpcap_inet_netof((__ptr_0 + 0))

  def inet_netof(_0 : Ptr[in_addr]): in_addr_t = 
    __sn_wrap_libpcap_inet_netof(_0)

  def inet_ntoa(_0 : Ptr[in_addr]): CString = 
    __sn_wrap_libpcap_inet_ntoa(_0)

  def inet_ntoa(_0 : in_addr)(using Zone): CString = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](1)
    !(__ptr_0 + 0) = _0
    __sn_wrap_libpcap_inet_ntoa((__ptr_0 + 0))

  def setipv4sourcefilter(_0 : CInt, _1 : in_addr, _2 : in_addr, _3 : uint32_t, _4 : uint32_t, _5 : Ptr[in_addr])(using Zone): CInt = 
    val __ptr_0: Ptr[in_addr] = alloc[in_addr](2)
    !(__ptr_0 + 0) = _1
    !(__ptr_0 + 1) = _2
    __sn_wrap_libpcap_setipv4sourcefilter(_0, (__ptr_0 + 0), (__ptr_0 + 1), _3, _4, _5)

  def setipv4sourcefilter(_0 : CInt, _1 : Ptr[in_addr], _2 : Ptr[in_addr], _3 : uint32_t, _4 : uint32_t, _5 : Ptr[in_addr]): CInt = 
    __sn_wrap_libpcap_setipv4sourcefilter(_0, _1, _2, _3, _4, _5)

