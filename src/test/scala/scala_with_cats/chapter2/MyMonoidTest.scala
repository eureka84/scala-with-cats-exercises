package scala_with_cats.chapter2

import MyMonoidLaws._
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class MyMonoidTest extends FunSuite with GeneratorDrivenPropertyChecks {

  test("OrMonoid") {
    import BooleanMonoids.orMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }

  test("AndMonoid") {
    import BooleanMonoids.andMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }

  test("xorMonoid") {
    import BooleanMonoids.xorMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }

  test("xNorMonoid") {
    import BooleanMonoids.xNorMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }

  test("unionMonoid") {
    import SetMonoids.unionMonoid

    forAll { (x: Set[Int], y: Set[Int], z: Set[Int]) => associativeLaw(x, y, z)}
    forAll { x: Set[Int] => identityLaw(x)}
  }

}
