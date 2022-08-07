package tcpdump4s

import scala.concurrent.duration.FiniteDuration
import com.comcast.ip4s.IpAddress
import scodec.{Attempt, Decoder, Err, codecs}
import scodec.bits.ByteVector
import fs2.protocols.ethernet.{EtherType, EthernetFrameHeader}
//import fs2.protocols.ip.IpHeader
import fs2.protocols.ip.{Ipv4Header, Ipv6Header}
import fs2.protocols.ip.tcp.TcpHeader
import fs2.protocols.ip.udp.DatagramHeader
import fs2.protocols.pcap.LinkType

opaque type IpHeader = Either[Ipv4Header, Ipv6Header]
object IpHeader:
  extension (self: IpHeader)
    def sourceIp: IpAddress = self.fold(_.sourceIp, _.sourceIp)
    def destinationIp: IpAddress = self.fold(_.destinationIp, _.destinationIp)
    def protocol: Int = self.fold(_.protocol, _.protocol)
  def apply(v4: Ipv4Header): IpHeader = Left(v4)
  def apply(v6: Ipv6Header): IpHeader = Right(v6)

object Ansi:
  val Faint = "\u001b[;2m"
  val Normal = "\u001b[;22m"

object Renderer:
  def values(header: Option[String], values: List[(String, Any)]): String =
    val bldr = new StringBuilder
    val h = header.getOrElse("")
    bldr.append(Ansi.Faint).append(f"${h}%-10s").append(Ansi.Normal)
    values.foldLeft(0) { case (acc, (label, value)) =>
      val valueStr = value.toString
      val newline = acc >= 70
      if newline then bldr.append("\n          ")
      bldr.append(Ansi.Faint).append(f"${label}%-5s").append(" ").append(Ansi.Normal).append(f"${valueStr}%-20s")
      val chars = label.size.max(5) + 1 + valueStr.size.max(20)
      val newlineAfter = valueStr.size > 20
      if newlineAfter then bldr.append("\n          ")
      if newlineAfter then 10 else (if newline then 0 else acc) + chars
    }
    bldr.toString

  def faint(s: String): String =
    val bldr = new StringBuilder
    bldr.append(Ansi.Faint).append(s).append(Ansi.Normal)
    bldr.toString

enum DecodedPacketPart:
  case Ethernet(value: EthernetFrameHeader)
  case Ip(value: IpHeader)
  case Tcp(value: TcpHeader)
  case Udp(value: DatagramHeader)
  case UnsupportedLinkType(linkType: LinkType)
  case UnsupportedEtherType(ethertype: Option[Int])
  case UnsupportedIpProtocol(protocol: Int, name: Option[String])

  def render: String = this match
    case Ethernet(v) =>
      Renderer.values(Some("Ethernet"), List(
        "src" -> v.source,
        "dst" -> v.destination))
    case Ip(v) =>
      Renderer.values(Some("IP"), List(
        "src" -> v.sourceIp,
        "dst" -> v.destinationIp))
    case Tcp(v) =>
      Renderer.values(Some("TCP"), List(
        "src" -> v.sourcePort,
        "dst" -> v.destinationPort,
        "seq" -> v.sequenceNumber,
        "ack" -> v.ackNumber,
        "win" -> v.windowSize))
    case Udp(v) =>
      Renderer.values(Some("UDP"), List("src" -> v.sourcePort, "dst" -> v.destinationPort))
    case UnsupportedLinkType(linkType) =>
      Renderer.faint(s"Unsupported link type ${linkType}")
    case UnsupportedEtherType(etherType) =>
      Renderer.faint(s"""Unsupported ether type ${etherType.getOrElse("n/a")}""")
    case UnsupportedIpProtocol(p, name) =>
      val nm = name.getOrElse(s"Proto $p")
      Renderer.faint(f"${nm}%-12s<undecoded>")

case class DecodedPacket(undecoded: ByteVector, parts: List[DecodedPacketPart], payload: ByteVector):
  def render(ts: FiniteDuration): String =
    val bldr = new StringBuilder
    val packetPart = Renderer.values(Some("Packet"), List("time" -> ts.toMillis, "len" -> undecoded.size))
    val rows = packetPart :: parts.map(_.render)
    bldr.append(rows.mkString("", "\n", "\n"))
    bldr.append(payload.toHexDumpColorized)
    bldr.toString

object DecodedPacket:

  private val decodeEthernet: Decoder[List[DecodedPacketPart]] =
    EthernetFrameHeader.codec.flatMap { ethernetHeader =>
      val part = DecodedPacketPart.Ethernet(ethernetHeader)
      ethernetHeader.ethertype match
        case Some(EtherType.IPv4) => decodeIpv4Header(part :: Nil)
        case Some(EtherType.IPv6) => decodeIpv6Header(part :: Nil)
        case o => Decoder.pure(DecodedPacketPart.UnsupportedEtherType(o) :: part :: Nil)
    }

  private def decodeIpv4Header(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    decodeIpHeader(acc, Ipv4Header.codec.map(IpHeader(_)))

  private def decodeIpv6Header(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    decodeIpHeader(acc, Ipv6Header.codec.map(IpHeader(_)))

  private def decodeIpHeader(acc: List[DecodedPacketPart], decoder: Decoder[IpHeader]): Decoder[List[DecodedPacketPart]] =
    decoder.flatMap { ipHeader =>
      val part = DecodedPacketPart.Ip(ipHeader)
      ipHeader.protocol match
        case 6 => decodeTcpHeader(part :: acc)
        case 17 => decodeUdpDatagram(part :: acc)
        case o =>
          val name = o match
            case 0 => Some("HOPOPT")
            case 1 => Some("ICMP")
            case 2 => Some("IGMP")
            case 58 => Some("IPv6-ICMP")
            case 59 => Some("IPv6-NoNxt")
            case 60 => Some("IPv6-Opts")
            case 115 => Some("L2TP")
            case _ => None
          Decoder.pure(DecodedPacketPart.UnsupportedIpProtocol(o, name) :: part :: acc)
    }

  private def decodeUdpDatagram(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    DatagramHeader.codec.map(udp => DecodedPacketPart.Udp(udp) :: acc)

  private def decodeTcpHeader(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    TcpHeader.codec.map(tcp => DecodedPacketPart.Tcp(tcp) :: acc)

  private def decodeIpv4OrIpv6Header(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    codecs.peek(codecs.uint4).flatMap {
      case 4 => decodeIpv4Header(acc)
      case _ => decodeIpv6Header(acc)
    }

  def decode(bytes: ByteVector, linkType: LinkType): Attempt[DecodedPacket] =
    val decoder = linkType match
      case LinkType.Ethernet => decodeEthernet
      case LinkType.Raw => decodeIpv4OrIpv6Header(Nil)
      case LinkType.Unknown(12) => decodeIpv4OrIpv6Header(Nil)
      case other => Decoder.pure(DecodedPacketPart.UnsupportedLinkType(other) :: Nil)
    val parts = decoder.decode(bytes.bits)
    parts.map(res => DecodedPacket(bytes, res.value.reverse, res.remainder.bytes))

