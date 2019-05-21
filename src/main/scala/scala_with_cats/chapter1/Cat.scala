package scala_with_cats.chapter1

final case class Cat(name: String, age: Int, color: String)

object CatInstances {

  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.eq._
  import cats.syntax.show._
  import cats.{Eq, Show}

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat.")

  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) => (cat1.name === cat2.name) &&
                                       (cat1.age === cat2.age) &&
                                       (cat1.color === cat2.color)
    }
}


object CatDemo extends App {

  import CatInstances._
  import cats.implicits._

  val garfield = Cat("Garfield", 38, "brown")
  val heathCliff = Cat("HeathCliff", 33, "orange and black")

  println(garfield.show)
  println(garfield === heathCliff)
}
