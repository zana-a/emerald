package io.zana.zapl.parser.call

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.Base.{identifier => baseIdentifier, _}
import io.zana.zapl.parser.primitive._

object Call {

  object Function {
    def params: Parser[Any] = {
      repsep(
        Primitive.`type` | baseIdentifier | Module.call | call,
        Keyword.COMMA
      )
    }

    def call: Parser[Any] = {
      baseIdentifier ~
        Keyword.LEFT_PARENTHESIS ~ params ~ Keyword.RIGHT_PARENTHESIS
    }
  }

  object Module {
    def call: Parser[Any] = {
      repsep(baseIdentifier, Keyword.BOX) ~ Function.call
    }
  }

  def call: Parser[Any] = {
    Module.call | Function.call
  }
}
