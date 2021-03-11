package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.predef.Integer
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}
import io.zana.zapl.structure.primitive

object Arithmetic extends Parsable[Any] {

  def apply: Parser[Any] = "1 + 1" ^^ (s => Structure(s))
}
