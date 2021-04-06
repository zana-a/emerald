package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.call.CallBody

case class List(value: scala.List[CallBody]) extends Primitive

