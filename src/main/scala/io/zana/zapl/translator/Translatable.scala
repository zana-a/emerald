package io.zana.zapl.translator

trait Translatable[T] {

  def apply(structure: T): String
}
