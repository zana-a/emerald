package io.zana.zapl.translator.expression

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.expression.{Pair, Single, Expression => Structure}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.Translatable

object Expression extends Translatable[Structure] {

  def sanitise(s: String): String = {
    if (s.head.toString == LEFT_PAREN && s.last.toString == RIGHT_PAREN)
      s.drop(1).dropRight(1)
    else
      s
  }

  override def apply(structure: Structure): String = {
    structure match {
      case primitive.String(value) => value
      case primitive.Boolean(value) => value.toString
      case primitive.Integer(value) => value.toString
      case Identifier(value) => value
      case Single(sym, e) => sym + e
      case Pair(sym, e1, e2) =>
        val l = apply(e1)
        val r = apply(e2)
        LEFT_PAREN + l + " " + sym + " " + r + RIGHT_PAREN
    }
  }
}
