package scala_with_cats.chapter4

import cats.data.Reader

object ReaderExercise {

  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String,
                    password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int,
                 password: String): DbReader[Boolean] =
    findUsername(userId)
      .flatMap(maybeUsername =>
        maybeUsername.fold
          (Reader[Db, Boolean](_ => false))
          (u => checkPassword(u, password))
      )

}
