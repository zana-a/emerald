package io.zana.zapl.parser.expression

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.expression.{Expression => Structure}

object Logic {

  def expression: Parser[Structure] = {

    def factor: Parser[Any] =
      NOT ~ factor | constant | LEFT_PAREN ~ expression ~ RIGHT_PAREN

    def constant: Parser[Any] =
      Arithmetic.expression | boolean | integer | identifier

    factor ~ opt(
      rep(AND ~ expression
        | OR ~ expression
        | EQEQ ~ expression
        | NEQ ~ expression
        | LT ~ expression
        | GT ~ expression
        | LTEQ ~ expression
        | GTEQ ~ expression
      )
    ) ^^ {
      _ => Structure("dummy logic")
    }
  }
}
