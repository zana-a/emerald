package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.control.Control._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.statement.Statement._

object Program {
  def build: Parser[Any] =
    rep(singleLineComment | statement | expression | control | call)
}
