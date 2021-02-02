package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

case class ModuleCall(body: List[Identifier], caller: FunctionCall) extends Callable
