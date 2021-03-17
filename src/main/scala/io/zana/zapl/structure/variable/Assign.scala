package io.zana.zapl.structure.variable

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.identifier.Identifier

case class Assign(name: Identifier, body: Any) extends BlockBody
