package io.zana.zapl.structure.primitive

import io.zana.zapl.structure.expression.Expression

case class Integer(value: Int) extends Primitive with Expression
