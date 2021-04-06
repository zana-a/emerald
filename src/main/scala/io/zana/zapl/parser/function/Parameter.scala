package io.zana.zapl.parser.function

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.statics.Static
import io.zana.zapl.structure.function.{Parameter => Structure}

object Parameter extends Parsable[Structure] {

  def apply: Parser[Structure] = Identifier.apply ~ (COLON ~> Static.apply) ^^ {
    case name ~ static => Structure(name, static)
  }
}
