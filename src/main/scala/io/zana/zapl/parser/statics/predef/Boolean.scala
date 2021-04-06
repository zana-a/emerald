package io.zana.zapl.parser.statics.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword.STATIC_T_BOOLEAN
import io.zana.zapl.structure.statics.{Boolean => Structure}

object Boolean extends Parsable[Structure.type] {

  override def apply: Parser[Structure.type] =
    STATIC_T_BOOLEAN ^^ (_ => Structure)
}
