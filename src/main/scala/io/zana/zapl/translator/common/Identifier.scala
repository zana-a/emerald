package io.zana.zapl.translator.common

import io.zana.zapl.structure.identifier
import io.zana.zapl.translator.Translatable

object Identifier extends Translatable[identifier.Identifier] {

  override def apply(structure: identifier.Identifier): String =
    structure.value
}
