package scala_with_cats.chapter4

import org.scalatest.{FunSpec, Matchers}

class EvalTest extends FunSpec with Matchers{
  val n: BigInt = 10000

  describe("foldRight via Eval"){
    it("my solution should preserve stack safety"){
      MySolution.foldRight((1 to n.intValue()).toList, 0) (_ + _) shouldBe(n * (n + 1)/ 2)
    }

    it(" book solution should preserve stack safety"){
      BookSolution.foldRight((1 to n.intValue()).toList, 0) (_ + _) shouldBe(n * (n + 1)/ 2)
    }

    it("naive implementation should produce a stack overflow"){
      assertThrows[StackOverflowError] {
        Naive.foldRight((1 to n.intValue()).toList, 0)(_ + _) shouldBe (n * (n + 1) / 2)
      }
    }
  }

}
