package scala_with_cats.chapter4

import scala_with_cats.chapter4.Id.Id

import scala.language.higherKinds

trait MyMonad[F[_]] {

  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(func andThen pure)

}

object Id {
  type Id[A] = A
}


object IdInstances {

  implicit val myIdMonad: MyMonad[Id] = new MyMonad[Id] {

    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)

  }

}
