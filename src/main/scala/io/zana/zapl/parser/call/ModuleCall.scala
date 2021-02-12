package io.zana.zapl.parser.call

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.call.{ModuleCall => Structure}

object ModuleCall extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    rep(identifier <~ BOX) ~ FunctionCall.apply ^^ {
      case ids ~ caller => Structure(ids, caller)
    }
  }
}
