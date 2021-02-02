package io.zana.zapl.parser.expression

import io.zana.zapl.parser

object Logic {

  import parser.Base._
  import Keyword._
  import parser.primitive.Primitive._

  def expression: Parser[Any] =
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
    )

  def factor: Parser[Any] =
    NOT ~ factor | constant | LEFT_PAREN ~ expression ~ RIGHT_PAREN

  def constant: Parser[Any] =
    Arithmetic.expression | boolean | integer | identifier
}
