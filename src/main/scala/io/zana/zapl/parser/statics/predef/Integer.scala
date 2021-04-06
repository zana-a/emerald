package io.zana.zapl.parser.statics.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword.STATIC_T_INT
import io.zana.zapl.structure.statics.{Integer => Structure}

object Integer extends Parsable[Structure.type] {

  override def apply: Parser[Structure.type] =
    STATIC_T_INT ^^ (_ => Structure)

}
