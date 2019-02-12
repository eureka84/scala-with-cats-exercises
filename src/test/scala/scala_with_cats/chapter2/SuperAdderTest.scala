package scala_with_cats.chapter2

import cats.Monoid
import org.scalatest.FunSuite
import cats.instances.int._
import cats.instances.option._

case class Order(totalCost: Double, quantity: Double)

object Order {
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0.0, 0.0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity +y.quantity)
  }
}

class SuperAdderTest extends FunSuite {

  test("adding List[Int]"){
    val sum = SuperAdder.add(List(1, 2, 3))
    assert(sum == 6)
  }

  test("adding List[Option[Int]]") {
    val maybeInt: Option[Int] = SuperAdder.add(List(Some(1), Some(3), None))
    assert(maybeInt.contains(4))
  }

  test("adding List[Order]") {
    val order = SuperAdder.add(List(Order(10.0, 2.4), Order(5.5, 2.6)))
    assert(order == Order(15.5, 5.0))
  }

}
