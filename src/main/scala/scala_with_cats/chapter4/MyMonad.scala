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


object MyMonadInstances {

  implicit val myIdMonad: MyMonad[Id] = new MyMonad[Id] {
    override def pure[A](a: A): Id[A] = a

    override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)
  }
}

object MyMonadSyntax {

  implicit class MonadOps[F[_], A](a: F[A]) {
    def flatMap[B](func: A => F[B])(implicit m: MyMonad[F]): F[B] = m.flatMap(a)(func)

    def map[B](func: A => B)(implicit m: MyMonad[F]): F[B] = m.map(a)(func)
  }

  implicit class ApplicativeOps[A](a: A) {
    def pure[F[_]](implicit m: MyMonad[F]): F[A] = m.pure(a)
  }
}
