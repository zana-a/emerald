package io.zana.zparser

trait ZParser {

  case class Parser[T](f: String => Option[(T, String)]) {
    def apply(in: String): Option[(T, String)] = f(in)

    def |(q: Parser[T]): Parser[T] = Parser(
      (in: String) => parse(this)(in) match {
        case s: Some[_] => s
        case None => parse(q)(in)
      }
    )

    def ~(q: Parser[T]): Parser[String] = Parser(
      (in: String) => parse(this)(in) match {
        case Some(value) => parse(q)(value._2) match {
          case Some(inner) => Some((value._1.toString + inner._1.toString), inner._2)
          case None => None
        }
        case None => None
      }
    )
  }

  def item: Parser[Char] = Parser(
    (in: String) => {
      in.toList match {
        case x :: xs => Some((x, xs.mkString))
        case _ => None
      }
    }
  )

  def tag(s: String): Parser[String] = Parser(
    (in: String) => {
      if (in.contains(s)) Some((s, in.drop(s.length)))
      else None
    }
  )

  def lowerAlpha: Parser[Char] = Parser(
    (in: String) => {
      in.toList match {
        case x :: xs =>
          if (x.isLower) Some((x, xs.mkString))
          else None
        case _ => None
      }
    }
  )

  def upperAlpha: Parser[Char] = Parser(
    (in: String) => {
      in.toList match {
        case x :: xs =>
          if (x.isUpper) Some((x, xs.mkString))
          else None
        case _ => None
      }
    }
  )

  def alpha: Parser[Char] = lowerAlpha | upperAlpha

  def digit: Parser[Char] = Parser(
    (in: String) => {
      in.toList match {
        case x :: xs =>
          if (x.isDigit) Some((x, xs.mkString))
          else None
        case _ => None
      }
    }
  )

  def parse[T](p: Parser[T])(in: String): Option[(T, String)] = p(in)
}
