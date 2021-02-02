package io.zana.zapl.parser.expression

import io.zana.zapl.parser

object Expression {

  import parser.Base._

  def expression: Parser[Any] =
    Logic.expression | Arithmetic.expression ^^ (expression => expression)
}
