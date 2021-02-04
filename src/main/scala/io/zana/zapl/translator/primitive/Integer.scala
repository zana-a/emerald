package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive.Integer
import io.zana.zapl.translator.Translatable

object Integer extends Translatable[Integer] {

  def translate(structure: Integer): String = structure.value.toString
}
