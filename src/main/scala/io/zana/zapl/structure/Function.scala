package io.zana.zapl.structure

case class Function(identifier: Identifier,
                    params: List[Identifier], body: Any)
