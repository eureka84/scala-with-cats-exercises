package scala_with_cats.chapter7

import org.scalatest.{FunSuite, Matchers}
import scala_with_cats.chapter7.TraverseExercise.listSequence

class TraverseExerciseTest extends FunSuite with Matchers {

  test("list vector"){

    import cats.instances.vector._
    import cats.syntax.apply._

    listSequence(List(Vector(1, 2), Vector(3, 4))) shouldBe
      Vector(List(1, 3), List(1, 4), List(2, 3), List(2, 4))

    (Vector(List()), Vector(1, 2)).mapN(_ :+ _) shouldBe
      Vector(List(1),List(2))

    (Vector(List(1),List(2)), Vector(3,4)).mapN(_ :+ _) shouldBe
      Vector(List(1, 3), List(1, 4), List(2, 3), List(2, 4))

  }

}
