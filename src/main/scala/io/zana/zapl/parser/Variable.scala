package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Block._
import io.zana.zapl.parser.Call._
import io.zana.zapl.parser.Control._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._
import io.zana.zapl.structure

object Variable {

  def variable: Parser[Any] = {
    (identifier <~ EQ) ~ (`type` /*TODO| expression | control | call | block*/)
    ^^ {
      case id ~ t => structure.variable.Variable(id, t)
    }
  }
}
