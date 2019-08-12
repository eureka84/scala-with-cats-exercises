package scala_with_cats.chapter6

import cats.data.Validated.{Invalid, Valid}
import cats.instances.either._
import cats.syntax.applicative._
import cats.syntax.either._
import org.scalatest.{FunSuite, Matchers}
import scala_with_cats.chapter6.FormValidation._

class FormValidationTest extends FunSuite with Matchers {

  val correctFormData: FormData = Map("age" -> "35", "name" -> "Angelo")
  val negativeAge: FormData = Map("age" -> "-35")
  val nonNumericAge: FormData = Map("age" -> "AA")
  val blankAge: FormData = Map("age" -> "")
  val blankName: FormData = Map("name" -> "")

  test("getValue") {
    getValue("age")(correctFormData) shouldBe "35".pure[FailFast]
    getValue("NonExisting")(correctFormData) shouldBe
      List("NonExisting field not specified").asLeft
  }

  test("parseInt") {
    parseInt("age")("11") shouldBe 11.pure[FailFast]
    parseInt("age")("a") shouldBe List("age is not a number").asLeft
  }

  test("non blank") {
    nonBlank("name")("Angelo") shouldBe "Angelo".pure[FailFast]
    nonBlank("name")("") shouldBe List("name cannot be blank").asLeft
  }

  test("non negative") {
    nonNegative("age")(25) shouldBe 25.pure[FailFast]
    nonNegative("age")(-21) shouldBe List("age must be non-negative").asLeft
  }

  test("readName") {
    readName(correctFormData) shouldBe "Angelo".pure[FailFast]
    readName(blankName) shouldBe List("name cannot be blank").asLeft
  }

  test("readAge") {
    readAge(correctFormData) shouldBe 35.pure[FailFast]
    readAge(blankAge) shouldBe List("age cannot be blank").asLeft
    readAge(nonNumericAge) shouldBe List("age is not a number").asLeft
    readAge(negativeAge) shouldBe List("age must be non-negative").asLeft
  }

  test("readUser") {
    readUser(correctFormData) shouldBe Valid(User("Angelo", 35))
    readUser(negativeAge) shouldBe
      Invalid(List("name field not specified", "age must be non-negative"))
  }

}
