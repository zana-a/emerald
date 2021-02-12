package io.zana.zapl.parser.call

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.call.Callable

object Call extends Parsable[Callable] {

  override def parse: Parser[Callable] =
    FunctionCall.parse | ModuleCall.parse
}
