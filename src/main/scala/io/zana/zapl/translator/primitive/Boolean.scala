package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive.Boolean
import io.zana.zapl.translator.Translatable

object Boolean extends Translatable[Boolean] {

  override def translate(structure: Boolean): String =
    structure.value.toString
}
