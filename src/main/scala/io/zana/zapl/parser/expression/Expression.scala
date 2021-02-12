package io.zana.zapl.parser.expression

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.expression.{Expression => Structure}

object Expression {

  def expression: Parser[Structure] =
    Logic.expression | Arithmetic.expression
}
