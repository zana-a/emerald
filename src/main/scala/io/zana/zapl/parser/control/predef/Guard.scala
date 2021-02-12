package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.expression.Logic
import io.zana.zapl.structure.expression.{Expression => Structure}

object Guard extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    Logic.expression
}
