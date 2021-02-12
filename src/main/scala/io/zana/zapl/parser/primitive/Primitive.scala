package io.zana.zapl.parser.primitive

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.primitive._

object Primitive {

  def string: Parser[String] = stringLiteral ^^ (result => String(result))

  def boolean: Parser[Boolean] = {
    def t: Parser[Boolean] = TRUE ^^ (result => Boolean(result.toBoolean))

    def f: Parser[Boolean] = FALSE ^^ (result => Boolean(result.toBoolean))

    t | f ^^ (result => result)
  }

  def list: Parser[List] = "[" ~> repsep(`type`, ",") <~ "]" ^^
    (result => List(result))

  def integer: Parser[Integer] = wholeNumber ^^
    (result => Integer(result.toInt))

  def `type`: Parser[Primitive] = string | integer | list | boolean
}
