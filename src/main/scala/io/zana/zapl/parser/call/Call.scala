package io.zana.zapl.parser.call

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.call.Callable

object Call {

  def call: Parser[Callable] =
    FunctionCall.call | ModuleCall.call
}
