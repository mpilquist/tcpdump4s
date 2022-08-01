package tcpdump4s

object Main {
  def main(args: Array[String]): Unit =
    Pcap.interfaces.foreach { interface =>
      println(interface.name)
      interface.addresses.foreach { addr =>
        println(s"  ${addr.cidr}")
      }
    }
}
