package scala_with_cats.chapter3

trait Codec[A] {

  self =>

  def encode(value: A): String

  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    override def encode(value: B): String = (enc andThen self.encode) (value)

    override def decode(value: String): B = dec(self.decode(value))
  }
}

object Codec {
  def encode[A](value: A)(implicit c: Codec[A]): String = c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A = c.decode(value)
}

object StringSyntax {
  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value

    override def decode(value: String): String = value
  }
}

object DoubleSyntax {
  implicit def doubleCodec(implicit codec: Codec[String]): Codec[Double] = codec.imap(_.toDouble, _.toString)
}

case class Box[A](value: A)

object Box {
  implicit def boxCodec[A](implicit codec: Codec[A]): Codec[Box[A]] = codec.imap(Box(_), _.value)
}