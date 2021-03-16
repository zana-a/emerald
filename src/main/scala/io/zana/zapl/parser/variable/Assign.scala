package io.zana.zapl.parser.variable

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword.EQ
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.variable.{Assign => Structure}

object Assign extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    Identifier.apply ~ (EQ ~> (Expression.apply | Primitive.apply | Call.apply)) ^^ {
      case id ~ body => Structure(id, body)
    }
}
