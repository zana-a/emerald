package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.control.Arm
import io.zana.zapl.structure.expression.{Expression, Literal}
import io.zana.zapl.structure.primitive.Boolean
import io.zana.zapl.structure.variable.Variable

object Default extends Parsable[Arm] {

  override def apply: Parser[Arm] = {
    (UNDERSCORE <~ FAT_ARROW) <~ Command.apply ^^ {
      command => Arm(Literal(1), command)
    }
  }
}

