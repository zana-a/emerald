package io.zana.zparser

object ZParser {

  trait Parser[T]

  case class Success[T](t: T) extends Parser[T]

  case class Failure[T](msg: String) extends Parser[T]

  def char(a: Char)(b: Char): Parser[Char] =
    if (a == b) Success(a)
    else Failure(s"$a != $b")

  def tag[T <: String](a: T)(b: T): Parser[T] =
    if (a == b) Success(a)
    else Failure(s"$a != $b")

  def or[T](a: String => Parser[T], b: String => Parser[T])(c: String): Parser[T] =
    a.apply(c) match {
      case s: Success[T] => Success(s.t)
      case _: Failure[T] => b.apply(c) match {
        case s: Success[T] =>Success(s.t)
        case _: Failure[T] =>Failure(s"Could not match $c")
      }
    }

  def parse[T](s: String)(p: String => Parser[T]): Parser[T] =
    p.apply(s)
}
