package io.zana.zapl.parser.program

import io.zana.zapl.{parser, structure}

object Program {

  import parser.Base._
  import parser.call.Call._
  import parser.comment.Comment._
  import parser.control.Control._
  import parser.function.Function._
  import parser.module.Module._
  import parser.variable.Variable._
  import structure.program.{Program => Result}

  def program: Parser[Result] = {
    rep(lineComment
      | module
      | call
      | function
      | variable
      | control
    )
  } ^^ (statements => Result(statements))
}
