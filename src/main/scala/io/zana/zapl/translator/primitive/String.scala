package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive.String
import io.zana.zapl.translator.Translatable

object String extends Translatable[String] {

  def translate(structure: String): Predef.String = structure.value
}
