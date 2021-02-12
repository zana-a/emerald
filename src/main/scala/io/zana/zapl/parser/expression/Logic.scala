package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.predef.{Boolean, Integer}
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}

object Logic extends Parsable[Structure] {

  override def apply: Parser[Structure] = {

    def factor: Parser[Any] =
      NOT ~ factor | constant | LEFT_PAREN ~ Expression.apply ~ RIGHT_PAREN

    def constant: Parser[Any] =
      Arithmetic.apply | Boolean.apply | Integer.apply | Identifier.apply

    factor ~ opt(
      rep(AND ~ Expression.apply
        | OR ~ Expression.apply
        | EQEQ ~ Expression.apply
        | NEQ ~ Expression.apply
        | LT ~ Expression.apply
        | GT ~ Expression.apply
        | LTEQ ~ Expression.apply
        | GTEQ ~ Expression.apply
      )
    ) ^^ {
      _ => Structure("dummy logic")
    }
  }
}
