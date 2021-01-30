package io.zana.zapl.parser.variable

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.block.Block._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.control.Control._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.variable.{Variable => Result}

object Variable {
  def variable: Parser[Result] = {
    (identifier <~ Keyword.EQ) ~ (`type`
      | expression
      | control
      | call
      | block) ^^ {
      case id ~ result => Result(id, result)
    }
  }
}
