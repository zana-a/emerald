package io.zana.zapl.parser.control

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.predef.{Arm, Default}
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.{Arm => AnArm, Cond => Structure}

object Cond extends Parsable[Structure] {

  private def mainArms: Parser[Option[List[AnArm]]] = opt(rep(Arm.apply)) ^^ {
    case Some(arms) => arms.length match {
      case 0 => None
      case _ => Some(arms)
    }
    case None => None
  }

  override def apply: Parser[Structure] =
    (COND <~ DO) ~> mainArms ~ opt(Default.apply) <~ END ^^ {
      case Some(arms) ~ Some(default) => Structure(Some(arms), Some(default))
      case Some(arms) ~ None => Structure(Some(arms), None)
      case None ~ Some(default) => Structure(None, Some(default))
      case None ~ None => Structure(None, None)
    }
}
