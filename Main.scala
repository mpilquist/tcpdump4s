package tcpdump4s

import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all.*
import com.monovore.decline.*
import fs2.Stream

object Main extends IOApp:

  def run(args: List[String]) =
    val command = Command(
      "tcpdump4s",
      "Network packet captures"
    ) {
      val showInterfacesOpt = Opts.flag("interfaces", "shows network interfaces supporting packet capture").as(showInterfaces)

      val captureOpt =
        val interface = Opts.option[String]("interface", short = "i", help = "name of the interface to capture on")
        val expression = Opts.argument[String](metavar = "expression").orNone
        (interface, expression).mapN(capture)

      showInterfacesOpt orElse captureOpt
    }

    command.parse(args) match
      case Left(help) => IO(System.err.println(help)).as(ExitCode(-1))
      case Right(prg) => prg.as(ExitCode.Success)

  def showInterfaces: IO[Unit] =
    Pcap.interfaces.flatMap { interfaces =>
      interfaces
        .filter(i => i.flags.isUp && i.flags.isRunning)
        .traverse_ { interface =>
          IO.println(interface.name) *>
            interface.addresses.traverse_ { addr =>
              IO.println(s"  ${addr.cidr}")
            }
        }
    }

  def capture(interface: String, expression: Option[String]): IO[Unit] =
    Pcap.livePackets(interface, false, expression)
      .flatMap((linkType, packets) => packets.map(_.map(b => DecodedPacket.decode(b, linkType).require)))
      .evalMap(t => IO.println(t.value.render(t.time)) *> IO.println(""))
      .compile.drain.as(ExitCode.Success)

