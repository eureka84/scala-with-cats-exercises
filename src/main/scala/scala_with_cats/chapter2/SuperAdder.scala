package scala_with_cats.chapter2

import cats.Monoid

object SuperAdder {

  def add[T: Monoid](items: List[T]): T = items.foldLeft(Monoid[T].empty)(Monoid[T].combine)

}
