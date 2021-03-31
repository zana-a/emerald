package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.{Translatable, primitive => translators}

object Primitive extends Translatable[primitive.Primitive] {

  override def apply(structure: Primitive): String = structure match {
    case e: primitive.Boolean => translators.Boolean(e)
    case e: primitive.Integer => translators.Integer(e)
    case e: primitive.List => translators.List(e)
    case e: primitive.String => translators.String(e)
    case e => throw new Error(e.toString)
  }
}