package io.zana.zapl.parser.call

import io.zana.zapl.{parser, structure}

object Call {

  import parser.Base._
  import structure.call.Callable

  def call: Parser[Callable] =
    FunctionCall.call | ModuleCall.call
}
