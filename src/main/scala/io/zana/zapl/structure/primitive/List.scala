package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.call.CallBody

// TODO: WHY CALLBODY????

case class List(value: scala.List[CallBody]) extends Primitive

