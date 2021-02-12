package io.zana.zapl.translator.statics

import io.zana.zapl.structure.statics
import io.zana.zapl.translator.Translatable

object Static extends Translatable[statics.Static] {

  override def translate(structure: statics.Static): String = {
    structure match {
      case statics.Integer => "Int"
      case statics.String => "String"
      case statics.Boolean => "Boolean"
      case statics.Any => "Any"
      case statics.List(generic) => s"List[$generic]"
    }
  }
}
