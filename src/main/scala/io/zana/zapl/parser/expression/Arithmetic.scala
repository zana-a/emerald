package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.predef.Integer
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}

object Arithmetic extends Parsable[Structure] {

  def apply: Parser[Structure] = {

    def expr: Parser[Any] = term ~ rep(MINUS ~ term | PLUS ~ term)

    def factor: Parser[Any] = Integer.apply | LEFT_PAREN ~ expr ~ RIGHT_PAREN

    def term: Parser[Any] =
      factor ~ rep(MULTIPLICATION ~ factor | DIVISION ~ factor)

    expr ^^ {
      _ => Structure("dummy")
    }
  }
}
