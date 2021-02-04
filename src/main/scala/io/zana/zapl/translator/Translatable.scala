package io.zana.zapl.translator

import io.zana.zapl.structure

trait Translatable {

  import structure.Structure

  def translate(structure: Structure): String
}
