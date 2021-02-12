package io.zana.zapl.parser.call

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.call.Callable

object Call extends Parsable[Callable] {

  override def apply: Parser[Callable] =
    FunctionCall | ModuleCall
}
