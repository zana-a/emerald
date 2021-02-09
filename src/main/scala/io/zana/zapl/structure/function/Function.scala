package io.zana.zapl.structure.function

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.common.StaticTypes.Static
import io.zana.zapl.structure.module.ModuleBody

case class Function(name: Identifier, params: List[Parameter], `return`: Static,
                    body: FunctionBody) extends Structure with ModuleBody
