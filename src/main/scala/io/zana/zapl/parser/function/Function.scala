package io.zana.zapl.parser.function

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.block.Block._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure

object Function {

  def function: Parser[Any] =
    (Keyword.DEF ~> identifier) ~
      ((Keyword.LEFT_PARENTHESIS ~> repsep(identifier, ",")) <~
        Keyword.RIGHT_PARENTHESIS) ~ (Keyword.EQ ~>
      (`type` | block | identifier /*TODO| call |expression| control  */)) ^^ {
      case id ~ params ~ body => {
        structure.function.Function(
          id,
          params,
          body
        )
      }
    }
}

