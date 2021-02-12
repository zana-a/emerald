package io.zana.zapl.parser.variable

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.function._
import io.zana.zapl.structure.variable.{Variable => Result}

object Variable {

  def id: Parser[Identifier] = identifier <~ EQ

  def body: Parser[FunctionBody] = `type` | call | expression

  def variable: Parser[Result] =
    id ~ body ^^ { case id ~ body => Result(id, body) }
}
