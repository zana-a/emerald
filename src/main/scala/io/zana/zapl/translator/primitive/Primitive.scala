package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator.{Translatable, primitive => translators}

object Primitive extends Translatable[primitive.Primitive] {

  override def apply(structure: primitive.Primitive): String = structure match {
    case primitive.Integer(_) =>
      translators
        .Integer
        .apply(structure.asInstanceOf[primitive.Integer])

    case primitive.String(_) =>
      translators
        .String
        .apply(structure.asInstanceOf[primitive.String])

    case primitive.Boolean(_) =>
      translators
        .Boolean
        .apply(structure.asInstanceOf[primitive.Boolean])

    case primitive.List(_) =>
      translators
        .List
        .apply(structure.asInstanceOf[primitive.List])
  }
}
