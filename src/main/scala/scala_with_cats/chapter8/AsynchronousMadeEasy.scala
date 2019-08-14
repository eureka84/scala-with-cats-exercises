package scala_with_cats.chapter8

import cats.Applicative
import cats.instances.list._
import cats.syntax.functor._
import cats.syntax.traverse._

import scala.concurrent.Future

object AsynchronousMadeEasy {

  trait UptimeClient[F[_]] {
    def getUptime(hostname: String): F[Int]
  }

  class UptimeService[F[_]: Applicative](client: UptimeClient[F]) {
    def getTotalUptime(hostnames: List[String]): F[Int] =
      hostnames.traverse(client.getUptime).map(_.sum)
  }

  trait ProductionUptimeClient extends UptimeClient[Future] {
    override def getUptime(hostname: String): Future[Int]
  }

}
