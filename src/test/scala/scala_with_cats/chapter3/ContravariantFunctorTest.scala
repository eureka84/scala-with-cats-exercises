package scala_with_cats.chapter3

import org.scalatest.FunSuite
import scala_with_cats.chapter1.Printable

class ContravariantFunctorTest extends FunSuite{

  test("printable"){
    import BoxSyntax._

    implicit val printableInt: Printable[Int] = (value: Int) => value.toString

    val aBox = Box[Int](123)

    assert(aBox.format() == "123")

  }

}
