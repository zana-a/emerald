package io.zana.zapl.structure.call

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.expression.Call
import io.zana.zapl.structure.function.FunctionBody
import io.zana.zapl.structure.program.ProgramBody

trait Callable extends FunctionBody
  with CallBody
  with BlockBody
  with Call
  with ProgramBody
