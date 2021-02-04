package io.zana.zapl.translator.primitive

import io.zana.zapl.structure

object String {

  import structure.primitive.String

  def translate(structure: String): Predef.String =
    structure.value
}
