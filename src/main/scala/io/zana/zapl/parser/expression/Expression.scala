package io.zana.zapl.parser.expression

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.primitive.Primitive._

object Expression {

  object Logic {
    def expression: Parser[Any] =
      factor ~ opt(
        rep(
          Keyword.AND ~ expression
            | Keyword.OR ~ expression
            | Keyword.EQEQ ~ expression
            | Keyword.NEQ ~ expression
            | Keyword.LT ~ expression
            | Keyword.GT ~ expression
            | Keyword.LTEQ ~ expression
            | Keyword.GTEQ ~ expression
        )
      )

    def factor: Parser[Any] =
      Keyword.NOT ~ factor | constant |
        Keyword.LEFT_PARENTHESIS ~ expression ~ Keyword.RIGHT_PARENTHESIS

    def constant: Parser[Any] =
      Arithmetic.expression | boolean | integer | identifier
  }

  object Arithmetic {
    def expression: Parser[Any] = {
      factor ~ opt(
        rep(
          Keyword.PLUS ~ expression
            | Keyword.MINUS ~ expression
            | Keyword.MULTIPLICATION ~ expression
            | Keyword.DIVISION ~ expression
        )
      )
    }

    def factor: Parser[Any] =
      constant | Keyword.LEFT_PARENTHESIS ~ expression ~ Keyword.RIGHT_PARENTHESIS

    def constant: Parser[Any] = integer | identifier
  }


  def expression: Parser[Any] =
    Logic.expression | Arithmetic.expression ^^ {
      case expression => expression
    }
}
