package scala_with_cats.chapter9

import org.scalatest.{FunSuite, Matchers}
import scala_with_cats.chapter9.MapReduce.foldMap

class MapReduceTest extends FunSuite with Matchers {

  test("foldMap") {

    import cats.instances.int._
    import cats.instances.string._

    foldMap(Vector(1, 2, 3))(identity) shouldBe 6

    foldMap(Vector(1, 2, 3))(_.toString + "! ") shouldBe "1! 2! 3! "
  }

}
