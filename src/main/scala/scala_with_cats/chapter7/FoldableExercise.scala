package scala_with_cats.chapter7

object FoldableExercise {

  def map[A, B](l: List[A])(f: A => B): List[B] =
    l.foldRight(List.empty[B])((el, acc) => f(el) :: acc)

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
    l.foldRight(List.empty[B])((el, acc) => f(el) ::: acc)

  def filter[A](l: List[A])(p: A => Boolean): List[A] =
    l.foldRight(List.empty[A])((el, acc) => if (p(el)) el :: acc else acc)

  def sum(l: List[Int]): Int = l.foldRight(0)(_ + _)

}
