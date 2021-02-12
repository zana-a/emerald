package io.zana.zapl.parser.function

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.statics.Static.static
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.function.{Parameter => Structure}

object Parameter extends Parsable[Structure] {

  def apply: Parser[Structure] = Identifier.apply ~ (COLON ~> static) ^^ {
    case name ~ static => Structure(name, static)
  }
}
