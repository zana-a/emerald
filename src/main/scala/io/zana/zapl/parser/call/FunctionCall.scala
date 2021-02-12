package io.zana.zapl.parser.call

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.call.{CallBody, FunctionCall => Structure}

object FunctionCall {

  def params: Parser[List[CallBody]] = {
    repsep(`type` | call | ModuleCall.call | identifier, COMMA)
  }

  def call: Parser[Structure] = {
    identifier ~ (LEFT_PAREN ~> params <~ RIGHT_PAREN) ^^ {
      case id ~ params => Structure(id, params)
    }
  }
}
