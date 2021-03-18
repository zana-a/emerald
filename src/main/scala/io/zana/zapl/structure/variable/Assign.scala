package io.zana.zapl.structure.variable

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.program.ProgramBody

case class Assign(name: Identifier, body: Any)
  extends BlockBody
    with ProgramBody
