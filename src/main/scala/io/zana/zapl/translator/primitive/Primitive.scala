package io.zana.zapl.translator.primitive

import io.zana.zapl.structure

object Primitive {

  import structure.primitive

  def translate(string: primitive.String): String = {
    string.value
  }

  def translate(integer: primitive.Integer): String = {
    integer.value.toString
  }

  def translate(boolean: primitive.Boolean): String = {
    boolean.value.toString
  }

  def translate(list: primitive.List): String = {
    ???
  }
}


