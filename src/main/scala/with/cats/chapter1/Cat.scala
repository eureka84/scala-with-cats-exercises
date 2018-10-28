package `with`.cats.chapter1

final case class Cat(name: String, age: Int, color: String)

object CatInstances {

  import cats.Show
  import cats.implicits._

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat.")

}


object CatDemo extends App {

  import CatInstances._
  import cats.syntax.show._

  val garfield = Cat("Garfield", 38, "brown")

//  import PrintableInstances._
//  import PrintableSyntax._
//
//  garfield.print()

  println(garfield.show)

}
