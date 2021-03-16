package io.zana.zapl.parser.variable

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.statics.Static
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.variable.{Variable => Structure}

object Variable extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    (LET ~> opt(MUT)) ~ Identifier.apply ~ (COLON ~> Static.apply) ~
      (EQ ~> (Expression.apply | Primitive.apply | Call.apply)) ^^ {
      case Some(_) ~ id ~ static ~ body =>
        Structure(modifiable = true, id, static, body)
      case None ~ id ~ static ~ body =>
        Structure(modifiable = false, id, static, body)
    }
  }
}
