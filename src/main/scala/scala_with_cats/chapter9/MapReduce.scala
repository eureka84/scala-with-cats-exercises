package scala_with_cats.chapter9

import cats.kernel.Monoid

object MapReduce {

  def foldMap[A, B: Monoid](sequence: Vector[A])(f: A => B): B =
    sequence.map(f).foldRight(Monoid[B].empty)(Monoid[B].combine)

}
