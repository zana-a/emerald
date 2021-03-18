package io.zana.zapl.structure.control

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.function.FunctionBody
import io.zana.zapl.structure.program.ProgramBody

trait Control
  extends FunctionBody
    with BlockBody
    with ProgramBody
