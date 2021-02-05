package io.zana.zapl.translator

trait Translatable[T] {

  def translate(structure: T): String
}
