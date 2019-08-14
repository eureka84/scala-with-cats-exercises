package scala_with_cats.chapter8

import cats.Id
import org.scalatest.FunSuite
import scala_with_cats.chapter8.AsynchronousMadeEasy.{UptimeClient, UptimeService}

class AsynchronousMadeEasyTest extends FunSuite {

  test("total uptime"){
    val hosts = Map("host1" -> 10, "host2" -> 6)
    val client = new TestUptimeClient(hosts)
    val service = new UptimeService(client)
    val actual = service.getTotalUptime(hosts.keys.toList)
    val expected = hosts.values.sum
    assert(actual == expected)
  }

}

class TestUptimeClient(hosts: Map[String, Int]) extends UptimeClient[Id] {
  def getUptime(hostname: String): Id[Int] = hosts.getOrElse(hostname, 0)
}
