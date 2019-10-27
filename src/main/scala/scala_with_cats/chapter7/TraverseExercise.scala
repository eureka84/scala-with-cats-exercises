package scala_with_cats.chapter7

import cats.Applicative
import cats.syntax.applicative._ // for pure
import cats.syntax.apply._ // for mapN

object TraverseExercise {

  import scala.language.higherKinds
  def listTraverse[F[_]: Applicative, A, B]
  (list: List[A])(func: A => F[B]): F[List[B]] =
    list.foldLeft(List.empty[B].pure[F]) { (accum, item) =>
      (accum, func(item)).mapN(_ :+ _)
    }
  def listSequence[F[_]: Applicative, B]
  (list: List[F[B]]): F[List[B]] =
    listTraverse(list)(identity)

}
