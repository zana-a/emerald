package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

//TODO change any
case class Function(name: Identifier, params: List[Any]) extends Caller
