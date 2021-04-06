package io.zana.zapl.parser.statics

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.statics.{Static => Structure}

object Static extends Parsable[Structure] {

  override def apply: Parser[Structure] = (
    predef.Integer.apply
      | predef.String.apply
      | predef.Boolean.apply
      | predef.List.apply
      | predef.Any.apply
    )
}
