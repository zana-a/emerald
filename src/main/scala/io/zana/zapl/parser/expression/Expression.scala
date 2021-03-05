package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}

object Expression extends Parsable[Structure] {

  override def apply: Parser[Structure] = Logic.apply

  //  | Arithmetic.apply
}
