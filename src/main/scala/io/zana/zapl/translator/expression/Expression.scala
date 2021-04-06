package io.zana.zapl.translator.expression

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.call.Callable
import io.zana.zapl.structure.expression.{Pair, Single, Expression => Structure}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object Expression extends Translatable[Structure] {

  def sanitise(s: String): String = {
    if (s.head.toString == LEFT_PAREN && s.last.toString == RIGHT_PAREN) s.drop(1).dropRight(1)
    else s
  }

  override def apply(structure: Structure): String = structure match {
    case e: Primitive => translator.primitive.Primitive(e)
    case e: Identifier => translator.identifier.Identifier(e)
    case Single(sym, e) => sym + apply(e)
    case Pair(sym, e1, e2) =>
      val l = apply(e1)
      val r = apply(e2)
      LEFT_PAREN + l + " " + sym + " " + r + RIGHT_PAREN
    case e: Callable => translator.call.Callable(e)
    case e => throw new Error(s"did not know how to translate $e")
  }
}
