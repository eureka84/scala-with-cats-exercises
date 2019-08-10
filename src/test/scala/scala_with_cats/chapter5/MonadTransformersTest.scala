package scala_with_cats.chapter5

import cats.data.OptionT
import cats.instances.list._
import cats.syntax.applicative._
import org.scalatest.{AsyncFunSuite, Matchers}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class MonadTransformersTest extends AsyncFunSuite with Matchers {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  test("OptionT"){
    type ListOption[A] = OptionT[List, A]

    val one: ListOption[Int] = OptionT(List(Option(10), Option(12)))
    val two: ListOption[Int] = 32.pure[ListOption]

    val result = two.flatMap( ex => one.map(in => ex + in))

    result shouldBe OptionT(List(Option(42), Option(44)))
  }

  test("Getting power level for a non existing autobot"){
    val result: Future[Either[String, Int]] = Transformers.getPowerLevel("Puppa").value

    result.map(e => e shouldBe Left("autobot Puppa not found"))
  }

  test("Getting power level for an existing autobot"){
    val result: Future[Either[String, Int]] = Transformers.getPowerLevel("Jazz").value

    result.map(e => e shouldBe Right(6))
  }

  test("cannot do a special move"){
    val result: Future[Either[String, Boolean]] = Transformers.canSpecialMove("Jazz", "Bumblebee").value

    result.map(e => e shouldBe Right(false))
  }

  test("can do a special move"){
    val result: Future[Either[String, Boolean]] = Transformers.canSpecialMove("Jazz", "Hot Rod").value

    result.map(e => e shouldBe Right(true))
  }

}
