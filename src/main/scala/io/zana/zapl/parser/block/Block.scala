package io.zana.zapl.parser.block

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.control.Control._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.variable.Variable._
import io.zana.zapl.structure

object Block {

  def block: Parser[structure.block.Block] = {
    Keyword.DO
    ~> rep (singleLineComment | variable | expression | control | call) <~
      Keyword.END ^^ {
      case values => structure.block.Block(values)
    }
  }
}
