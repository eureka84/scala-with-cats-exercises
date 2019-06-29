package scala_with_cats.chapter4

import org.scalatest.FunSuite
import scala_with_cats.chapter3.{Branch, Leaf, Tree}
import TreeMonad._

class TreeMonadTest extends FunSuite {

  test("flatMap"){
    val result = Tree.branch(
      Tree.leaf(100),
      Tree.leaf(200)
    ).flatMap(x =>
      Tree.branch(
        Tree.leaf(x - 1),
        Tree.leaf(x + 1)
      )
    )
    assert(result ==
      Branch(
        Branch(
          Leaf(99),
          Leaf(101)
        ),
        Branch(
          Leaf(199),
          Leaf(201)
        )
      )
    )
  }

}
