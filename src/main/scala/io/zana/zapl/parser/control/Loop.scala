package io.zana.zapl.parser.control

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.predef.{Arm, Default}
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.{Loop => Structure}

object Loop extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    (LOOP ~ DO) ~> (opt(rep(Arm.apply)) ~ Default.apply) <~ END ^^ {
      case Some(arms) ~ default => Structure(arms, default)
    }
}
