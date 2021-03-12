package io.zana.zapl.parser.call.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.call.{CallBody, FunctionCall => Structure}

object FunctionCall extends Parsable[Structure] {

  def params: Parser[List[CallBody]] = repsep(
    Primitive.apply
      | this.apply
      | ModuleCall.apply
      | Identifier.apply,
    COMMA
  )

  override def apply: Parser[Structure] = {
    Identifier.apply ~ (LEFT_PAREN ~> params <~ RIGHT_PAREN) ^^ {
      case id ~ params => Structure(id, params)
    }
  }
}
