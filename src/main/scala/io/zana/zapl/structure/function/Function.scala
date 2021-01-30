package io.zana.zapl.structure.function

import io.zana.zapl.structure.common.Identifier

case class Function(identifier: Identifier,
                    params: List[Identifier], body: Any)
