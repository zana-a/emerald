package io.zana.zapl.parser.statics.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword.{LEFT_ANGLE, STATIC_T_LIST}
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.statics.{Integer => Structure}

object List extends Parsable[Structure.type] {

  //todo wrappable
  override def apply: Parser[Structure.type] =
    STATIC_T_LIST ~> (LEFT_ANGLE ~> static <~ RIGHT_ANGLE) ^^
      (generic => Structure.List(generic))
}
