package scala_with_cats.chapter3

trait Printable[A] {

  self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = (value: B) => (func andThen self.format) (value)

}

object BoxSyntax {

//  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]]
//  = (box: Box[A]) => p.format(box.value)

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = p.contramap[Box[A]](_.value)

}