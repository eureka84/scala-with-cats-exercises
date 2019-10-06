package scala_with_cats.chapter7

import scala.math.Numeric

object FoldableExercise {

  def map[A, B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])((el, acc) => f(el) :: acc)

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
    l.foldRight(List.empty[B])((el, acc) => f(el) ::: acc)

  def filter[A](l: List[A])(p: A => Boolean): List[A] =
    l.foldRight(List.empty[A])((el, acc) => if (p(el)) el :: acc else acc)

  def sum[A](l: List[A])(implicit n: Numeric[A]): A = l.foldRight(n.zero)(n.plus)

}
