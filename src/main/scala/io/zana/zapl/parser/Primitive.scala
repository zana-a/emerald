package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.structure.Type

object Primitive {

  def boolean: Parser[Type.Boolean] = {
    def t: Parser[Type.Boolean] = TRUE ^^ {
      result => Type.Boolean(result.toBoolean)
    }

    def f: Parser[Type.Boolean] = FALSE ^^ {
      result => Type.Boolean(result.toBoolean)
    }

    t | f ^^ (result => result)
  }

  def string: Parser[Type.String] = stringLiteral ^^ {
    result => Type.String(result)
  }

  def list: Parser[Type.List] = "[" ~> repsep(`type`, ",") <~ "]" ^^ {
    result => Type.List(result)
  }

  def integer: Parser[Type.Integer] = wholeNumber ^^ {
    result => Type.Integer(result.toInt)
  }

  def `type`: Parser[Type.Type[_]] = string | integer | list | boolean
}