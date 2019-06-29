package scala_with_cats.chapter4

import cats.Monad
import scala_with_cats.chapter3.{Branch, Leaf, Tree}

object TreeMonad {

  implicit val treeMonad: Monad[Tree] = new Monad[Tree] {
    override def pure[A](x: A): Tree[A] = Tree.leaf(x)

    override def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]): Tree[B] = fa match {
      case Leaf(value) => f(value)
      case Branch(l, r) => Branch(flatMap(l)(f), flatMap(r)(f))
    }

    override def tailRecM[A, B](arg: A)(func: A => Tree[Either[A, B]]): Tree[B] =
      func(arg) match {
        case Branch(l, r) =>
          Branch(
            flatMap(l) {
              case Left(l) => tailRecM(l)(func)
              case Right(l) => pure(l)
            },
            flatMap(r) {
              case Left(r) => tailRecM(r)(func)
              case Right(r) => pure(r)
            }
          )
        case Leaf(Left(value)) => tailRecM(value)(func)
        case Leaf(Right(value)) => Leaf(value)
      }

  }

  implicit class TreeSyntax[A](t: Tree[A]) {
    def flatMap[B](f: A => Tree[B])(implicit monad: Monad[Tree]): Tree[B] = monad.flatMap(t)(f)
  }
}
