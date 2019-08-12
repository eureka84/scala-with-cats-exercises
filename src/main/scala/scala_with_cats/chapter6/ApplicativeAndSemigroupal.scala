package scala_with_cats.chapter6

object ApplicativeAndSemigroupal {

  case class Cat(name: String, yearOfBirth: Int, favoriteFoods: List[String])
  val tupleToCat: (String, Int, List[String]) => Cat = Cat.apply
  val catToTuple: Cat => (String, Int, List[String]) = cat => (cat.name, cat.yearOfBirth, cat.favoriteFoods)

  import cats.Monoid
  import cats.instances.int._
  import cats.instances.invariant._
  import cats.instances.list._
  import cats.instances.string._
  import cats.syntax.apply._

  implicit val catMonoid: Monoid[Cat] = (
    Monoid[String],
    Monoid[Int],
    Monoid[List[String]]
  ).imapN(tupleToCat)(catToTuple)


  import cats.Monad
  import cats.syntax.flatMap._
  import cats.syntax.functor._
  def product[M[_]: Monad, A, B](fa: M[A], fb: M[B]): M[(A, B)] =
    fa.flatMap(a => fb.map(b => (a, b)))


}
