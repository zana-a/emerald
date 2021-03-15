package io.zana.zapl.parser.expression.predef

object Conversion {

  def toInt(a: Boolean): Int = if (a) 1 else 0

  def toInt(a: String): Int = a.toInt

  def toBoolean(a: Int): Boolean = a != 0
}
