package scala_with_cats.chapter4

import org.scalatest.FunSuite
import scala_with_cats.chapter4.Id.Id

class MonadTest extends FunSuite {

  test("Id") {
    import MyMonadInstances._
    import MyMonadSyntax._

    val a: Id[Int] = 4.pure[Id]
    val b: Id[Int] = 5.pure[Id]

    def square[F[_]](x: F[Int], y: F[Int])(implicit m: MyMonad[F]): F[Int] =
      m.flatMap(x)(a => m.map(y)(b => a * a + b * b))

    assertResult(square(a, b))(41)
  }

}
