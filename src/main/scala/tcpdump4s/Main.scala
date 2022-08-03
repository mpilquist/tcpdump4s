package tcpdump4s

import cats.effect.{IO, IOApp}
import cats.syntax.all.*

object Main extends IOApp.Simple {
  def run: IO[Unit] =
    Pcap.interfaces.flatMap { interfaces =>
      interfaces.traverse_ { interface =>
        IO.println(interface.name) *>
          interface.addresses.traverse_ { addr =>
            IO.println(s"  ${addr.cidr}")
          }
      }
    }
}
