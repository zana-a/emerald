package io.zana.zapl.parser.call

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.call.{ModuleCall => Structure}

object ModuleCall {

  def call: Parser[Structure] = {
    rep(identifier <~ BOX) ~ FunctionCall.call ^^ {
      case ids ~ caller => Structure(ids, caller)
    }
  }
}
