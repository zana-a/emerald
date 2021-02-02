package io.zana.zapl.parser.call

import io.zana.zapl.{parser, structure}

object ModuleCall {

  import parser.Base._
  import Keyword._
  import structure.call.{ModuleCall => Result}

  def call: Parser[Result] =
    rep(identifier <~ BOX) ~ FunctionCall.call ^^ {
      case ids ~ caller => Result(ids, caller)
    }
}
