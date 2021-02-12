package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.{Arm => Structure}

object Arm extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    (Guard.apply <~ FAT_ARROW) ~ Command.apply ^^ {
      case guard ~ command => Structure(guard, command)
    }
}
