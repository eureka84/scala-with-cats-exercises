package scala_with_cats.chapter3

import org.scalatest.FunSuite
import Codec._
import StringSyntax._
import DoubleSyntax._
class InvariantFunctorTest extends FunSuite {

  test("encode Double"){
    val double: Double = 123.4
    assert(encode(double) == "123.4")
  }

  test("decode Double"){
    assert(decode[Double]("123.4") == 123.4)
  }

  test("encode box"){
    assert(encode(Box(123.4)) == "123.4")
  }

  test("decode Box"){
    assert(decode[Box[Double]]("123.4") == Box(123.4))
  }

}
