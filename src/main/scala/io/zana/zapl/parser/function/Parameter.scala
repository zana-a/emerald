package io.zana.zapl.parser.function

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.statics.Static.static
import io.zana.zapl.structure.function.{Parameter => Structure}

object Parameter {

  def parameter: Parser[Structure] = identifier ~ (COLON ~> static) ^^ {
    case name ~ static => Structure(name, static)
  }
}
