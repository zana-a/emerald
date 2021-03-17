package io.zana.zapl.structure.call

import io.zana.zapl.structure.identifier.Identifier

case class ModuleCall(modules: List[Identifier], caller: FunctionCall)
  extends Callable

