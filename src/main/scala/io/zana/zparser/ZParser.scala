package io.zana.zparser

object ZParser {

  // an object of parser that takes a function `f` that is a higher order
  // function which takes a string and returns an option of parser state. If
  // parsed then a result of Some(T, Next) where T is any type and Next is a
  // remaining parse string
  case class Parser[T](f: String => Option[(T, String)]) {
    def apply(in: String): Option[(T, String)] = f(in)
  }

  // a function wrapped in a parser object that is a higher order function which
  // accepts an `in` of string and returns Some or None tuple containing T and Next.
  def item: Parser[String] = Parser(
    (in: String) => {
      in.toList match {
        case x :: xs => Some((x.toString, xs.mkString))
        case _ => None
      }
    }
  )

  // the parse function takes a generic parser `p` and a curried function
  // that takes the parameter in which is the input data by the consumer.
  def parse[T](p: Parser[T])(in: String): Option[(T, String)] =
    p(in)
}
