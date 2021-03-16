package io.zana.zapl.parser.function

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.block.Block
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.control.Control
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.statics.Static
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.function.{FunctionBody, Function => Structure}

object Function extends Parsable[Structure] {

  override def apply: Parser[Structure] = {

    val id = DEF ~> Identifier.apply

    val params = LEFT_PAREN ~> repsep(Parameter.apply, COMMA) <~ RIGHT_PAREN

    val body: Parser[FunctionBody] = EQ ~> (
      Expression.apply
        | Primitive.apply
        | Block.apply
        | Call.apply
        | Identifier.apply
        | Control.apply
      )

    val static = COLON ~> Static.apply

    (id ~ params ~ static ~ body) ^^ {
      case id ~ params ~ static ~ body => Structure(id, params, static, body)
    }
  }
}
