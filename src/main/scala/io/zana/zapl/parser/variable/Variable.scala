package io.zana.zapl.parser.variable

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.function._
import io.zana.zapl.structure.variable.{Variable => Structure}

object Variable extends Parsable[Structure] {

  override def apply: Base.Parser[Structure] = {
    def id: Parser[Identifier] = identifier <~ EQ

    def body: Parser[FunctionBody] = Primitive.apply | call | expression

    id ~ body ^^ { case id ~ body => Structure(id, body) }
  }

}
