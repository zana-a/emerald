package io.zana.zapl.parser.primitive

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.primitive.{Primitive => Structure}

object Primitive extends Parsable[Structure] {

  override def apply: Parser[Structure] = (
    predef.String.apply
      | predef.Integer.apply
      | predef.Boolean.apply
      | predef.List.apply
    )
}
