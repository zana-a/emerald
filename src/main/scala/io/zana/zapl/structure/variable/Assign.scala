package io.zana.zapl.structure.variable

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.statics.Static

case class Assign(name: Identifier, body: Any) extends BlockBody
