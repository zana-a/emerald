package io.zana.zapl.structure.call

import io.zana.zapl.structure.common.Identifier

case class Module(body: List[Identifier], caller: Function)
