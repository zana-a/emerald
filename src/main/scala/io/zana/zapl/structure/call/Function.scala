package io.zana.zapl.structure.call

import io.zana.zapl.structure.identifier.Identifier

case class Function(name: Identifier, params: List[CallBody])
  extends Callable
