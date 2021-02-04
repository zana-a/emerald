package io.zana.zapl.translator

import io.zana.zapl.structure.Structure

trait Translatable {

  def translate[T <: Structure](structure: T): String
}
