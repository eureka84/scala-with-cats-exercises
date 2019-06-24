package scala_with_cats.chapter4

import cats.Eval

object BookSolution {

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) => b.map(fn(a, _)) }.value

}


object MySolution {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = foldRightEval(as, acc)(fn).value

  def foldRightEval[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail => {
        Eval.defer(foldRightEval(tail, acc)(fn)).map { evalTail => fn(head, evalTail) }
      }
      case Nil => {
        Eval.now(acc)
      }
    }
}

object Naive {
  // not stack safe because the recursive call is not tail recursive, i.e. it's not the last function call in that branch
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

}