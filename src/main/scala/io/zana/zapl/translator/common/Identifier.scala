package io.zana.zapl.translator.common

import io.zana.zapl.structure.common
import io.zana.zapl.translator.Translatable

object Identifier extends Translatable[common.Identifier] {

  override def apply(structure: common.Identifier): String =
    structure.value
}
