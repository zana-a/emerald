package io.zana.zapl.parser.call.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.call.{ModuleCall => Structure}

object ModuleCall extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    rep(Identifier.apply <~ BOX) ~ FunctionCall.apply ^^ {
      case ids ~ caller => Structure(ids, caller)
    }
  }
}
