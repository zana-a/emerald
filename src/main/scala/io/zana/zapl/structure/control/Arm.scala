package io.zana.zapl.structure.control

import io.zana.zapl.structure.expression.Expression

case class Arm(guard: Expression, command: Any)

