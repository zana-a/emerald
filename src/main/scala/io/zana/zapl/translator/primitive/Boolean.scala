package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.Translatable

object Boolean extends Translatable[primitive.Boolean] {

  override def translate(structure: primitive.Boolean): String =
    structure.value.toString
}
