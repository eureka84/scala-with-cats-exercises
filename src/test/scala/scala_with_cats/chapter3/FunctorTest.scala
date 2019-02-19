package scala_with_cats.chapter3

import org.scalatest.FunSuite
import TreeSyntax._

class FunctorTest extends FunSuite {

  val f = { x: Int => 2 * x + 1 }

  test("map on a leaf") {
    val aLeaf = Leaf(2)

    assert(aLeaf.map(f) == Leaf(5))
  }

  test("map on a branch"){
    val aBranch: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    assert(aBranch.map(f) == Branch(Branch(Leaf(3), Leaf(5)), Leaf(7)))
  }

}
