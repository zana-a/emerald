package io.zana.zparser

object ZParser {

  trait ParseResult[T] {

    def isFailure: Boolean = this match {
      case Success(_, _) => false
      case Failure(_) => true
      case e => throw new Error(s"Unknown ParseResult $e")
    }

    def nonFailure: Boolean = this match {
      case Success(_, _) => true
      case Failure(_) => false
      case e => throw new Error(s"Unknown ParseResult $e")
    }
  }

  case class Success[T](result: T, next: String = "") extends ParseResult[T]

  case class Failure[T](msg: String) extends ParseResult[T]

  implicit def charToString(c: Char): String = c.toString

  def tag(s: String)(t: String): ParseResult[String] =
    if (t.nonEmpty)
      if (t.contains(s)) Success(s, t.drop(s.length))
      else Failure(s"expected $s found $t")
    else Success(t)

  def parse[T](parser: String => ParseResult[T])(in: String): ParseResult[T] = {
    parser(in)
  }

  def or[T](a: String => ParseResult[T], b: String => ParseResult[T])
           (c: String): ParseResult[T] = {
    val res1 = a(c)
    val res2 = b(c)

    if (res1.nonFailure) res1
    else res2
  }
}

