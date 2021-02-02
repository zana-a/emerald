package io.zana.zapl.parser.program

import io.zana.zapl.parser

object Program {

  import parser.Base._
  import parser.call.Call._
  import parser.comment.Comment._
  import parser.control.Control._
  import parser.expression.Expression._
  import parser.variable.Variable._

  def build: Parser[Any] = {
    rep(lineComment
      | call
      | variable
      | expression
      | control
    )
  }
}
