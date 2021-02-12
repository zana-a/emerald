package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

case class Module(modules: List[Identifier], caller: Function)
  extends Callable
