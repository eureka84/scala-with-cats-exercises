package scala_with_cats.chapter5

import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.{ExecutionContext, Future}

object Transformers {

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  type Response[A] = EitherT[Future, String, A]

  def getPowerLevel(ally: String)(implicit ec: ExecutionContext): Response[Int] =
    powerLevels.get(ally) match {
      case Some(value) => EitherT.right(Future(value))
      case None => EitherT.left(Future(s"autobot $ally not found"))
    }

  def canSpecialMove(ally1: String, ally2: String)(implicit ec: ExecutionContext): Response[Boolean] =
    for {
      p1 <- getPowerLevel(ally1)
      p2 <- getPowerLevel(ally2)
    } yield (p1 + p2) >= 15

}
