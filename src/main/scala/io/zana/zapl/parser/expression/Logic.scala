package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.predef.{Boolean, Integer}
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}

object Logic extends Parsable[Structure] {

  // TODO: HARD CODED!
  override def apply: Parser[Structure] = "true && true" ^^ (s => Structure(s))
}
