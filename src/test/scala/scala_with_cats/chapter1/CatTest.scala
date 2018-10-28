package scala_with_cats.chapter1

import org.scalatest.FunSuite

class CatTest extends FunSuite {

  test("cat equality") {

    import cats.Eq
    import cats.syntax.eq._
    import scala_with_cats.chapter1.CatInstances._

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("HeathCliff", 33, "orange and black")

    assert(!Eq[Cat].eqv(cat1, cat2))

    assert(Eq[Cat].eqv(cat1, cat1))
    assert(Eq[Cat].eqv(cat2, cat2))
    assert(cat1 =!= cat2)
  }


}
