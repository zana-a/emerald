package io.zana.zapl.parser.function

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.Control
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.statics.Static._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.function.{FunctionBody, Function => Structure}

object Function extends Parsable[Structure] {

  override def apply: Parser[Structure] = {

    val id = DEF ~> Identifier.apply

    val params = LEFT_PAREN ~> repsep(Parameter.apply, COMMA) <~ RIGHT_PAREN

    val body: Parser[FunctionBody] = EQ ~> (
      Primitive.apply
        | call
        | Identifier.apply
        | Expression.apply
        | block
        | Control.apply
      )

    val `return` = COLON ~> static

    (id ~ params ~ `return` ~ body) ^^ {
      case id ~ params ~ r ~ body => Structure(id, params, r, body)
    }
  }
}
