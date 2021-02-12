package io.zana.zapl.parser.control

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.predef.{Arm, Default}
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.{Cond => Structure}

object Cond extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    (COND <~ DO) ~> (opt(rep(Arm.apply)) ~ Default.apply) <~ END ^^ ???
}
