package io.zana.zapl.parser.function

import io.zana.zapl.parser.Base.Keyword._
import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.function.{Parameter => Result}

object Parameter {

  def parameter: Parser[Result] = identifier ~ (COLON ~> staticType) ^^ {
    case name ~ static => Result(name, static)
  }
}
