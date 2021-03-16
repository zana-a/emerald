package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.expression.Expression

case class Boolean(value: scala.Boolean) extends Primitive with Expression
