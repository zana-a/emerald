package io.zana.zapl.parser.function

import io.zana.zapl.{parser, structure}

object Function {

  import parser.Base._
  import Keyword._
  import parser.block.Block._
  import parser.call.Call._
  import parser.control.Control._
  import parser.expression.Expression._
  import parser.primitive.Primitive._
  import structure.function.{Function => Result}

  def function: Parser[Result] = {
    val id = DEF ~> identifier

    val params =
      LEFT_PAREN ~> repsep(identifier, ",") <~ RIGHT_PAREN

    val body = EQ ~> (`type`
      | call
      | identifier
      | expression
      | block
      | control)

    (id ~ params ~ body) ^^ {
      case id ~ params ~ body => Result(id, params, body)
    }
  }
}
