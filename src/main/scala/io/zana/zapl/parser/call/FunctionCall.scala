package io.zana.zapl.parser.call

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.call.{CallBody, FunctionCall => Structure}

object FunctionCall extends Parsable[Structure] {

  def params: Parser[List[CallBody]] = {
    repsep(`type` | this.parse | ModuleCall.parse | identifier, COMMA)
  }

  override def parse: Parser[Structure] = {
    identifier ~ (LEFT_PAREN ~> params <~ RIGHT_PAREN) ^^ {
      case id ~ params => Structure(id, params)
    }
  }
}
