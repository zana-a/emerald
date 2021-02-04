package io.zana.zapl.structure.function

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.common.Identifier

case class Function(name: Identifier,
                    params: List[Identifier], body: Any) extends Structure
