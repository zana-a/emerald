package io.zana.zapl.parser.variable

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure

object Variable {

  def variable: Parser[Any] =
    (identifier <~ Keyword.EQ) ~
      `type` /*TODO| expression | control | call | block*/ ^^ {
      case id ~ t => structure.variable.Variable(id, t)
    }
}
