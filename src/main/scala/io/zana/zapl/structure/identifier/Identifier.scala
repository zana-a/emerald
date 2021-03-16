package io.zana.zapl.structure.identifier

import io.zana.zapl.structure.call.CallBody
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.function.FunctionBody

case class Identifier(value: String)
  extends FunctionBody
    with CallBody
    with Expression
