package scala_with_cats.chapter2

import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class MyMonoidTest extends FunSuite with GeneratorDrivenPropertyChecks {

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: MyMonoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: MyMonoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

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


}
