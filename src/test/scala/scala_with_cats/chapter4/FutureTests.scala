package scala_with_cats.chapter4

import cats.Monad
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import cats.instances.future._
import scala.concurrent.ExecutionContext.Implicits.global

object FutureTests extends App {
  val fm: Monad[Future] = Monad[Future]
  val future = fm.flatMap(fm.pure(1))(x => fm.pure(x + 2))
  println(Await.result(future, 1.second))
}
