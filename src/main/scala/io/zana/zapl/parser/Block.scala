package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Call._
import io.zana.zapl.parser.Comment._
import io.zana.zapl.parser.Control._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Function._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.parser.Variable._
import io.zana.zapl.structure

object Block {

  def block: Parser[structure.block.Block] = {
    DO
    ~> rep (singleLineComment | variable | expression | control | call) <~
      END ^^ {
      case values => structure.block.Block(values)
    }
  }
}