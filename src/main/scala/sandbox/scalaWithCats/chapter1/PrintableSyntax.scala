package sandbox.scalaWithCats.chapter1

object PrintableSyntax {

  implicit class PrintableOps[A] (a: A) {

    def format()(implicit printable: Printable[A]): String = printable.format(a)

    def print()(implicit printable: Printable[A]): Unit = println(printable.format(a))

  }

}
