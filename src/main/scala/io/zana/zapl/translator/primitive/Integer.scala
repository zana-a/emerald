package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.Translatable

object Integer extends Translatable[primitive.Integer] {

  def translate(structure: primitive.Integer): String =
    structure.value.toString
}
