package scala_with_cats.chapter7

import org.scalatest.{FunSuite, Matchers}
import scala_with_cats.chapter7.FoldableExercise.{filter, flatMap, map, sum}

class FoldableExerciseTest extends FunSuite with Matchers {

  test("map"){
    map(List(1,2,3))(_ * 2) shouldBe List(2, 4, 6)
  }

  test("flatMap"){
    val digits = (x: Int) => x.toString.map(_.toString.toInt).toList
    flatMap(List(1, 12, 123))(digits) shouldBe List(1, 1, 2, 1, 2, 3)
  }

  test("filter"){
    filter(List(1, 2, 3, 4))(_ % 2 == 0) shouldBe List(2, 4)
  }

  test("sum"){
    sum(List(1, 2, 3)) shouldBe 6
  }
}
