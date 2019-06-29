package scala_with_cats.chapter4

object StateMonadExercise {

  import cats.data.State
  import cats.syntax.applicative._

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] =
    sym match {
      case "+" => operator(_ + _)
      case "-" => operator(_ - _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case num => operand(num.toInt)
    }

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack => (num :: stack, num)}

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val ans = func(a, b)
        (ans :: tail, ans)
      case _ =>
        sys.error("Fail!")
    }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a.flatMap(_ => evalOne(b))
    }
//  { // MINE
//    val list: List[CalcState[Int]] = input.map(s => evalOne(s))
//    list.reduce((s1, s2) => s1.flatMap(_ => s2))
//  }

  def evalInput(s: String): CalcState[Int] = evalAll(s.split(" ").toList)

}
