package scala_with_cats.chapter3

trait Printable[A] {

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    (value: B) => (func andThen format) (value)

}

object BoxSyntax {

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)


  implicit class BoxOps[A](b: Box[A]) {
    def format()(implicit printable: Printable[Box[A]]): String = printable.format(b)
  }
}