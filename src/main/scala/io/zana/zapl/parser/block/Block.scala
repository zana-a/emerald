package io.zana.zapl.parser.block

import io.zana.zapl.{parser, structure}

object Block {

  import parser.Base._
  import Keyword._
  import parser.call.Call._
  import parser.comment.Comment._
  import parser.control.Control._
  import parser.expression.Expression._
  import parser.variable.Variable._
  import structure.block.{Block => Result}

  def block: Parser[Result] = {
    DO ~> rep(
      lineComment
        | expression
        | variable
        | control
        | call
    ) <~ END ^^ (values => Result(values))
  }
}
