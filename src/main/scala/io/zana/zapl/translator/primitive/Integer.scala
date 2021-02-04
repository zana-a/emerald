package io.zana.zapl.translator.primitive

import io.zana.zapl.structure

object Integer {

  import structure.primitive

  def translate(structure: primitive.Integer): String =
    structure.value.toString

}
