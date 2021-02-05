package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

//TODO change any
case class FunctionCall(name: Identifier, params: List[Any])
  extends Callable
