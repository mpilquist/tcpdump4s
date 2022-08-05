package tcpdump4s

import cats.effect.{IO, IOApp}
import cats.syntax.all.*

import fs2.Stream

object Main extends IOApp.Simple {
  def run: IO[Unit] = dumpUdpOnEn0

  def dumpUdpOnEn0: IO[Unit] =
    Stream.resource(Pcap.openLive("en0", false))
      .evalTap(_.setFilter("udp"))
      .flatMap(p => Stream.repeatEval(p.next))
      .evalMap(t =>
        IO.println(t.time) *> IO(t.value.printHexDump()) *> IO.println("")
      )
      .compile.drain

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
