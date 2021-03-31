package io.zana.zparser

trait ZParser {

  // an object of parser that takes a function `f` that is a higher order
  // function which takes a string and returns an option of parser state. If
  // parsed then a result of Some(T, Next) where T is any type and Next is a
  // remaining parse string
  case class Parser[T](f: String => Option[(T, String)]) {
    def apply(in: String): Option[(T, String)] = f(in)

    def |(q: Parser[T]): Parser[T] = Parser(
      (in: String) => parse(this)(in) match {
        case s: Some[_] => s
        case None => parse(q)(in)
      }
    )

    def n: Parser[Nothing] = ???

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

  // a function wrapped in a parser object that is a higher order function which
  // accepts an `in` of string and returns Some or None tuple containing T and Next.
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

  // the parse function takes a generic parser `p` and a curried function
  // that takes the parameter in which is the input data by the consumer.
  def parse[T](p: Parser[T])(in: String): Option[(T, String)] = p(in)
}
