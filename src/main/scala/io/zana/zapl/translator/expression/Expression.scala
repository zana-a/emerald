package io.zana.zapl.translator.expression

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.translator.Translatable
import io.zana.zapl.structure.expression.{Pair, Single, Expression => Structure}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive

object Expression extends Translatable[Structure] {
  override def apply(structure: Structure): String = {
    structure match {
      case primitive.String(value) => value
      case primitive.Boolean(value) => value.toString
      case primitive.Integer(value) => value.toString
      case Identifier(value) => value
      case Single(sym, e) => sym + e
      case Pair(sym, e1, e2) => {
        val l = apply(e1)
        val r = apply(e2)
        LEFT_PAREN + l + sym + r + RIGHT_PAREN
      }
    }
  }
}
