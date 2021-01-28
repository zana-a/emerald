package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Block._
import io.zana.zapl.parser.Call._
import io.zana.zapl.parser.Control._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._
import io.zana.zapl.structure

object Function {

  def function: Parser[Any] = {
    (DEF ~> identifier) ~
      ((LEFT_PARENTHESIS ~> repsep(identifier, ",")) <~
        RIGHT_PARENTHESIS) ~ (EQ ~>
      (`type` | block | identifier /*TODO| call | control | expression*/))
    ^^ {
      case id ~ params ~ body => {
        structure.function.Function(
          id,
          params,
          body
        )
      }
    }
  }
}
