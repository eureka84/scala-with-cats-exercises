package scala_with_cats.chapter4

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._
import org.scalatest.FunSuite

class WriterTest extends FunSuite {

  //  def factorial(n: Int): Writer[Vector[String], Int] = {
  //    if (n == 0)
  //        1.writer(Vector("fact 0 1"))
  //    else
  //      factorial(n - 1).flatMap(prev => Writer(Vector(s"fact $n ${prev * n}"), prev * n))
  //  }

  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  type Logged[A] = Writer[Vector[String], A]

  def factorial(n: Int): Logged[Int] =
    for {
      ans <- if (n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans
//   OR
//    (if (n == 0) {
//      1.pure[Logged]
//    } else {
//      slowly(factorial(n - 1).map(_ * n))
//    }).flatMap(ans =>
//        Vector(s"fact $n $ans").tell.map(_ => ans)
//    )


  test("writer factorial") {
    val (log, res) = factorial(5).run

    assertResult(120)(res)
    assertResult(Vector(
      "fact 0 1",
      "fact 1 1",
      "fact 2 2",
      "fact 3 6",
      "fact 4 24",
      "fact 5 120"
    ))(log)
  }

}
