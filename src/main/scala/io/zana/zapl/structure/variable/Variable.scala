package io.zana.zapl.structure.variable

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.common.Identifier

case class Variable(name: Identifier, body: Any) extends BlockBody
