package scala_with_cats.chapter4

import org.scalatest.FunSuite
import scala_with_cats.chapter4.ReaderExercise.Db
import ReaderExercise._

class ReaderExerciseTest extends FunSuite {

  val users: Map[Int, String] = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords: Map[String, String] = Map(
    "dade" -> "zerocool",
    "kate" -> "acidburn",
    "margo" -> "secret"
  )
  val db = Db(users, passwords)

  test("correct login") {
    assert(checkLogin(1, "zerocool").run(db))
  }

  test("non existing user") {
    assert(!checkLogin(4, "davinci").run(db))
  }


}
