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

    override def combine(x: Boolean, y: Boolean): Boolean =
      (x && !y) || (!x && y)
  }

  implicit val xNorMonoid: MyMonoid[Boolean] = new MyMonoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x || y) && (x || !y)
  }

}
