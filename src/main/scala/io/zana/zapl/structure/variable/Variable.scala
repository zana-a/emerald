package io.zana.zapl.structure.variable

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.program.ProgramBody
import io.zana.zapl.structure.statics.Static

case class Variable(modifiable: Boolean, name: Identifier,
                    static: Option[Static], body: Any)
  extends BlockBody
    with ProgramBody
