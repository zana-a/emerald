package io.zana.zapl.parser.expression

import io.zana.zapl.{parser, structure}

object Expression {

  import parser.Base._

  def expression: Parser[structure.expression.Expression] =
    Logic.expression | Arithmetic.expression
}
