package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.{Translatable, primitive => translators}

object Primitive extends Translatable[primitive.Primitive] {

  override def translate(structure: primitive.Primitive): String = structure match {
    case primitive.Integer(_) =>
      translators
        .Integer
        .translate(structure.asInstanceOf[primitive.Integer])

    case primitive.String(_) =>
      translators
        .String
        .translate(structure.asInstanceOf[primitive.String])

    case primitive.Boolean(_) =>
      translators
        .Boolean
        .translate(structure.asInstanceOf[primitive.Boolean])

    case primitive.List(_) =>
      translators
        .List
        .translate(structure.asInstanceOf[primitive.List])
  }
}
