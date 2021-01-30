package io.zana.zapl.parser.call

import io.zana.zapl.parser.Base.{identifier => baseIdentifier, _}
import io.zana.zapl.parser.primitive._
import io.zana.zapl.structure.call.Caller

object Call {

  object Function {

    import io.zana.zapl.structure.call.{Function => Result}

    def call: Parser[Result] = {
      baseIdentifier ~
        (Keyword.LEFT_PARENTHESIS ~> params <~ Keyword.RIGHT_PARENTHESIS) ^^ {
        case id ~ params => Result(id, params)
      }
    }

    def params: Parser[List[Any]] = {
      repsep(
        Primitive.`type` | call | Module.call | baseIdentifier,
        Keyword.COMMA
      )
    }
  }

  object Module {

    import io.zana.zapl.structure.call.{Module => Result}

    def call: Parser[Result] = {
      rep(baseIdentifier <~ Keyword.BOX) ~ Function.call ^^ {
        case ids ~ caller => Result(ids, caller)
      }
    }
  }

  def call: Parser[Caller] = {
    Function.call | Module.call
  }
}
