package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.Structure

case class List(value: scala.List[Type]) extends Structure with Type
