package tcpdump4s

import cats.effect.{IO, IOApp}
import cats.syntax.all.*

object Main extends IOApp.Simple {
  def run: IO[Unit] =
    Pcap.openLive("en0", false).flatTap(_.setFilter("udp")).flatMap(IO.println)

  def showInterfaces: IO[Unit] =
    Pcap.interfaces.flatMap { interfaces =>
      interfaces.traverse_ { interface =>
        IO.println(interface.name) *>
          interface.addresses.traverse_ { addr =>
            IO.println(s"  ${addr.cidr}")
          }
      }
    }
}
