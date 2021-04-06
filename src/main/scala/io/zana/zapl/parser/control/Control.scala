package io.zana.zapl.parser.control

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.control.{Control => Structure}

object Control extends Parsable[Structure] {

  override def apply: Parser[Structure] = Cond.apply
}
