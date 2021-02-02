package io.zana.zapl.parser.variable

import io.zana.zapl.{parser, structure}

object Variable {

  import parser.Base._
  import Keyword._
  import parser.block.Block._
  import parser.call.Call._
  import parser.control.Control._
  import parser.expression.Expression._
  import parser.primitive.Primitive._
  import structure.variable.{Variable => Result}

  def variable: Parser[Result] = {
    val id = identifier <~ EQ
    val body = `type` | expression | control | call | block

    id ~ body ^^ { case id ~ body => Result(id, body) }
  }
}
