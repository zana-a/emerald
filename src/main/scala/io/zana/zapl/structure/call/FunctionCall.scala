package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

case class FunctionCall(name: Identifier, params: List[CallBody])
  extends Callable
