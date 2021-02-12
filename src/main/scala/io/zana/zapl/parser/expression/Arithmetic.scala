package io.zana.zapl.parser.expression

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.expression.{Expression => Structure}

object Arithmetic {

  def expression: Parser[Structure] = {

    def expr: Parser[Any] = term ~ rep(MINUS ~ term | PLUS ~ term)

    def factor: Parser[Any] = integer | LEFT_PAREN ~ expr ~ RIGHT_PAREN

    def term: Parser[Any] =
      factor ~ rep(MULTIPLICATION ~ factor | DIVISION ~ factor)

    expr ^^ {
      _ => Structure("dummy")
    }
  }
}
