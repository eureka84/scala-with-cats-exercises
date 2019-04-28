package scala_with_cats.chapter1

object PrintableInstances {

  implicit val stringPrintable: Printable[String] = (a: String) => a

  implicit val intPrintable: Printable[Int] = (a: Int) => a.toString

  implicit val catPrintable: Printable[Cat] = (cat: Cat) => {
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }

}
