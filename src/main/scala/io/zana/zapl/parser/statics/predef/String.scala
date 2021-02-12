package io.zana.zapl.parser.statics.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword.STATIC_T_STRING
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.statics.{String => Structure}

object String extends Parsable[Structure.type] {

  override def apply: Parser[Structure.type] =
    STATIC_T_STRING ^^ (_ => Structure)
}
