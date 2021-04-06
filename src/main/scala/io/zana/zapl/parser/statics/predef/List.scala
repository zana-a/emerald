package io.zana.zapl.parser.statics.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.statics.Static
import io.zana.zapl.structure.statics.{List => Structure}

object List extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    STATIC_T_LIST ~> (LEFT_ANGLE ~> Static.apply <~ RIGHT_ANGLE) ^^
      (generic => Structure(generic))
}
