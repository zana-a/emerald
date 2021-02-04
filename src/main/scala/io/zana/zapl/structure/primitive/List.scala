package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.Structure

case class List(values: scala.List[Type]) extends Structure with Type
