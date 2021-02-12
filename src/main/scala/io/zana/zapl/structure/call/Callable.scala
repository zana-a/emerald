package io.zana.zapl.structure.call

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.function.FunctionBody

trait Callable extends FunctionBody
  with CallBody
  with BlockBody {

  override def toString: String =
    "call." ++ super.toString
}
