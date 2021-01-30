package io.zana.zapl.parser.call

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.Base.{identifier => baseIdentifier, _}
import io.zana.zapl.parser.primitive._

object Call {

  object Function {
    def params: Parser[Any] = {
      repsep(
        Primitive.`type` | baseIdentifier | call | Module.call,
        Keyword.COMMA
      )
    }

    def call: Parser[Any] = {
      baseIdentifier ~ Keyword.LEFT_PARENTHESIS ~ params ~ Keyword.RIGHT_PARENTHESIS
    }
  }

  object Module {
    def identifier: Parser[Any] =
      baseIdentifier ~ rep(Keyword.BOX ~> baseIdentifier)

    def call: Parser[Any] =
      identifier ~ Keyword.BOX ~ Function.call
  }

  def call: Parser[Any] =
    Module.call | Function.call

}
