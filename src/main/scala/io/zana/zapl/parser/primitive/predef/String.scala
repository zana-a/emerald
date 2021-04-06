package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.primitive.{String => Structure}

object String extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    stringLiteral ^^ (s => Structure(s))
}
