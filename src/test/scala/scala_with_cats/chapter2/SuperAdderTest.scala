package scala_with_cats.chapter2

import org.scalatest.FunSuite

import cats.instances.int._
import cats.instances.option._

class SuperAdderTest extends FunSuite {

  test("adding List[Int]"){
    val sum = SuperAdder.add(List(1, 2, 3))
    assert(sum == 6)
  }

  test("adding List[Option[Int]]") {
    val maybeInt: Option[Int] = SuperAdder.add(List(Some(1), Some(3), None))
    assert(maybeInt.contains(4))
  }

}
