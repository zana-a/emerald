package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.Translatable

object String extends Translatable[primitive.String] {

  def translate(structure: primitive.String): Predef.String =
    structure.value
}