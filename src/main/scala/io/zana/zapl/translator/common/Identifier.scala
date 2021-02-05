package io.zana.zapl.translator.common

import io.zana.zapl.structure.common
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.translator.Translatable

object Identifier extends Translatable[common.Identifier] {
  override def translate(structure: Identifier): String =
    structure.name
}
