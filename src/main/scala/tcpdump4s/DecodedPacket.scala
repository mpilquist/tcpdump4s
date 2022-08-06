package tcpdump4s

import com.comcast.ip4s.IpAddress
import scodec.{Decoder, Err, codecs}
import scodec.bits.ByteVector
import fs2.protocols.ethernet.{EtherType, EthernetFrameHeader}
//import fs2.protocols.ip.IpHeader
import fs2.protocols.ip.{Ipv4Header, Ipv6Header}
import fs2.protocols.ip.udp.DatagramHeader

opaque type IpHeader = Either[Ipv4Header, Ipv6Header]
object IpHeader:
  extension (self: IpHeader)
    def sourceIp: IpAddress = self.fold(_.sourceIp, _.sourceIp)
    def destinationIp: IpAddress = self.fold(_.destinationIp, _.destinationIp)
    def protocol: Int = self.fold(_.protocol, _.protocol)
  def apply(v4: Ipv4Header): IpHeader = Left(v4)
  def apply(v6: Ipv6Header): IpHeader = Right(v6)

enum DecodedPacketPart:
  case Ethernet(value: EthernetFrameHeader)
  case Ip(value: IpHeader)
  case Udp(value: DatagramHeader)
  case UnsupportedEtherType(ethertype: Option[Int])
  case UnsupportedIpProtocol(protocol: Int)

  def render: String = this match
    case Ethernet(v) => f"Ethernet: src: ${v.source}%-20s dst: ${v.destination}"
    case Ip(v)       => f"IP:       src: ${v.sourceIp}%-20s dst: ${v.destinationIp}"
    case Udp(v)      => f"UDP:      src: ${v.sourcePort}%-20s dst: ${v.destinationPort}"
    case UnsupportedEtherType(etherType) => s"""Unsupported ether type ${etherType.getOrElse("n/a")}"""
    case UnsupportedIpProtocol(p) => s"Unsupported IP protocol $p"

case class DecodedPacket(parts: List[DecodedPacketPart]):
  def render: String = parts.map(_.render).mkString("", "\n", "")

object DecodedPacket:

  private val decoder: Decoder[DecodedPacket] =
    EthernetFrameHeader.codec.flatMap { ethernetHeader =>
      val part = DecodedPacketPart.Ethernet(ethernetHeader)
      val next = ethernetHeader.ethertype match
        case Some(EtherType.IPv4) => decodeIpv4Header(part :: Nil)
        case Some(EtherType.IPv6) => decodeIpv6Header(part :: Nil)
        case o => Decoder.pure(DecodedPacketPart.UnsupportedEtherType(o) :: part :: Nil)
      next.map(parts => DecodedPacket(parts.reverse))
    }

  private def decodeIpv4Header(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    decodeIpHeader(acc, Ipv4Header.codec.map(IpHeader(_)))

  private def decodeIpv6Header(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    decodeIpHeader(acc, Ipv6Header.codec.map(IpHeader(_)))

  private def decodeIpHeader(acc: List[DecodedPacketPart], decoder: Decoder[IpHeader]): Decoder[List[DecodedPacketPart]] =
    decoder.flatMap { ipHeader =>
      val part = DecodedPacketPart.Ip(ipHeader)
      ipHeader.protocol match
        case 17 => decodeUdpDatagram(part :: acc)
        case o => Decoder.pure(DecodedPacketPart.UnsupportedIpProtocol(o) :: part :: acc)
    }

  private def decodeUdpDatagram(acc: List[DecodedPacketPart]): Decoder[List[DecodedPacketPart]] =
    DatagramHeader.codec.map(udp => DecodedPacketPart.Udp(udp) :: acc)

  def decode(bytes: ByteVector) =
    decoder.decode(bytes.bits)

