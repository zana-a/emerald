package io.zana.zapl.parser.control

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.predef.{Arm, Default}
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.control.{Cond => Structure}

object Cond extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    (COND <~ DO) ~> Arm.arms ~ opt(Default.apply) <~ END ^^ {
      case Some(arms) ~ Some(default) => Structure(Some(arms), Some(default))
      case Some(arms) ~ None => Structure(Some(arms), None)
      case None ~ Some(default) => Structure(None, Some(default))
      case None ~ None => Structure(None, None)
    }
}
