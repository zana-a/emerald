package io.zana.zapl.parser.program

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.control.Control._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.statement.Statement._
import io.zana.zapl.parser.variable.Variable._

object Program {
  def build: Parser[Any] =
      rep(singleLineComment | call | variable | statement | expression | control)
}
