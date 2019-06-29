package scala_with_cats.chapter4

import org.scalatest.FunSuite
import StateMonadExercise._

class StateMonadExerciseTest extends FunSuite {

  test("evalAll"){
    val program: CalcState[Int] = evalAll(List("1", "2", "+", "3", "*"))

    assert(program.runA(Nil).value == 9)
  }

  test("complex eval"){
    val program = for {
      _ <- evalAll(List("1", "2", "+"))
      _ <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans

    assert(program.runA(Nil).value == 21)
  }

  test("eval input"){
    assert(evalInput("1 2 + 3 * 4 +").runA(Nil).value == 13)
  }

}
