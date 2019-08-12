package scala_with_cats.chapter6

import cats.data.Validated
import cats.instances.list._
import cats.syntax.either._
import cats.syntax.apply._

object FormValidation {

  case class User(name: String, age: Int)

  type FormData = Map[String, String]
  type FailFast[A] = Either[List[String], A]
  type FailSlow[A] = Validated[List[String], A]

  def readUser(rawFormData: FormData): FailSlow[User] =
    (
      readName(rawFormData).toValidated,
      readAge(rawFormData).toValidated
    ).mapN(User.apply)

  def readName(rawFormData: FormData): FailFast[String] =
    for {
      rawData   <- getValue("name")(rawFormData)
      name      <- nonBlank("name")(rawData)
    } yield name

  def readAge(rawFormData: FormData): FailFast[Int] =
    for {
      rawData   <- getValue("age")(rawFormData)
      _         <- nonBlank("age")(rawData)
      rawNumber <- parseInt("age")(rawData)
      age       <- nonNegative("age")(rawNumber)
    } yield age

  def getValue (fieldName: String)(data: FormData): FailFast[String] =
    data.get(fieldName).toRight(List(s"$fieldName field not specified"))

  def parseInt(field: String)(data: String): FailFast[Int] =
    Either.catchOnly[NumberFormatException](data.toInt)
          .leftMap(_ => List(s"$field is not a number"))

  def nonBlank(name: String)(data: String): FailFast[String] =
    Right(data).
      ensure(List(s"$name cannot be blank"))(_.nonEmpty)

  def nonNegative(name: String)(data: Int): FailFast[Int] =
    Right(data).
  ensure(List(s"$name must be non-negative"))(_ >= 0)

}
