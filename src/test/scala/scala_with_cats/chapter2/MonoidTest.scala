package scala_with_cats.chapter2

import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class MonoidTest extends FunSuite with GeneratorDrivenPropertyChecks {

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }

  test("OrMonoid") {
    import Monoids.booleanOrMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }

  test("AndMonoid") {
    import Monoids.booleanAndMonoid
    forAll { (x: Boolean, y: Boolean, z: Boolean) => associativeLaw(x, y, z) }
    forAll { x: Boolean => identityLaw(x) }
  }


}
