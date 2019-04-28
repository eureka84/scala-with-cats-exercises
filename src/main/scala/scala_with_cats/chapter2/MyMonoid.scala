package scala_with_cats.chapter2

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait MyMonoid[A] extends Semigroup[A] {
  def empty: A
}

object MyMonoid {
  def apply[A](implicit monoid: MyMonoid[A]): MyMonoid[A] = monoid
}

object MyMonoidLaws {
  def associativeLaw[A](x: A, y: A, z: A)(implicit m: MyMonoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: MyMonoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
  }
}

object BooleanMonoids {

  implicit val orMonoid: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val andMonoid: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val xorMonoid: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x != y
  }

  implicit val xNorMonoid: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x == y
  }
}

object SetMonoids {

  implicit def unionMonoid[A]: MyMonoid[Set[A]] = new MyMonoid[Set[A]] {
    override def empty: Set[A] = Set()

    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

}
