package io.zana.zapl.parser.primitive

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.primitive

object Primitive {

  def string: Parser[primitive.String] = {
    stringLiteral ^^ (result => primitive.String(result))
  }

  def boolean: Parser[primitive.Boolean] = {
    def t: Parser[primitive.Boolean] = Keyword.TRUE ^^ {
      result => primitive.Boolean(result.toBoolean)
    }

    def f: Parser[primitive.Boolean] = Keyword.FALSE ^^ {
      result => primitive.Boolean(result.toBoolean)
    }

    t | f ^^ (result => result)
  }

  def list: Parser[primitive.List] = "[" ~> repsep(`type`, ",") <~ "]" ^^ {
    result => primitive.List(result)
  }

  def integer: Parser[primitive.Integer] = wholeNumber ^^ {
    result => primitive.Integer(result.toInt)
  }

  def `type`: Parser[primitive.Type] = string | integer | list | boolean
}
