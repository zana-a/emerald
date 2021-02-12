package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.{Arm => Structure}
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.primitive.Boolean

object Default extends Parsable[Structure] {


  override def apply: Parser[Structure] = {
    val underscore = UNDERSCORE ^^ (_ => Boolean(true))

    (underscore <~ FAT_ARROW) ~ Command.apply ^^ {
      case guard ~ command => Structure(Expression(guard.toString), command)
    }
  }
}
