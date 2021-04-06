package io.zana.zapl.parser.call.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.structure.call.{CallBody, FunctionCall => Structure}

object FunctionCall extends Parsable[Structure] {

  def params: Parser[List[CallBody]] = repsep(
    Expression.apply
      | Primitive.apply
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
