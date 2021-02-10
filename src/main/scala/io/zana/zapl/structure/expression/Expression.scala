package io.zana.zapl.structure.expression

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.function.FunctionBody

case class Expression(value: String)
  extends FunctionBody
    with BlockBody
