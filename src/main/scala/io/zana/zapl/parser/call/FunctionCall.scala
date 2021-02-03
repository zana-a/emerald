package io.zana.zapl.parser.call

import io.zana.zapl.{parser, structure}

object FunctionCall {

  import parser.Base._
  import Keyword._
  import parser.primitive.Primitive._
  import structure.call.{FunctionCall => Result}

  def params: Parser[List[Any]] =
    repsep(`type` | call | ModuleCall.call | identifier, COMMA)

  def call: Parser[Result] =
    identifier ~ (LEFT_PAREN ~> params <~ RIGHT_PAREN) ^^ {
      case id ~ params => Result(id, params)
    }
}
