package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}

object Primitive {

  import parser.Base._
  import structure.primitive._

  def string: Parser[String] = {
    stringLiteral ^^ (result => String(result))
  }

  def boolean: Parser[Boolean] = {
    def t: Parser[Boolean] = Keyword.TRUE ^^ {
      result => Boolean(result.toBoolean)
    }

    def f: Parser[Boolean] = Keyword.FALSE ^^ {
      result => Boolean(result.toBoolean)
    }

    t | f ^^ (result => result)
  }

  def list: Parser[List] = "[" ~> repsep(`type`, ",") <~ "]" ^^ {
    result => List(result)
  }

  def integer: Parser[Integer] = wholeNumber ^^ {
    result => Integer(result.toInt)
  }

  def `type`: Parser[Type] = string | integer | list | boolean
}
