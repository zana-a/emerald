package io.zana.zapl.structure.function

import io.zana.zapl.Structure
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.module.ModuleBody

case class Function(name: Identifier, params: List[Parameter], body: Any)
  extends Structure with ModuleBody
