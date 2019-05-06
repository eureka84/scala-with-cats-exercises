package scala_with_cats.chapter3

import cats.Contravariant
import scala_with_cats.chapter1.Printable

object PrintableInstances {

  implicit val contravariantInstance: Contravariant[Printable] = new Contravariant[Printable] {
    override def contramap[A, B](fa: Printable[A])(f: B => A): Printable[B] =
      (value: B) => (f andThen fa.format) (value)
  }


}

object BoxSyntax {

  import PrintableInstances._
  import cats.syntax.contravariant._

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)


  implicit class BoxOps[A](b: Box[A]) {
    def format()(implicit printable: Printable[Box[A]]): String = printable.format(b)
  }
}