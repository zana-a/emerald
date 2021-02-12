package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.primitive.{Integer => Structure}

object Integer extends Parsable[Structure] {

  override def apply: Parser[Structure] = wholeNumber ^^
    (result => Structure(result.toInt))
}
