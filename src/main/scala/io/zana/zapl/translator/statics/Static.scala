package io.zana.zapl.translator.statics

import io.zana.zapl.structure.statics
import io.zana.zapl.translator.Translatable

object Static extends Translatable[statics.String.Static] {

  override def translate(structure: statics.String.Static): String = {
    structure match {
      case statics.String.Int => "Int"
      case statics.String.String => "String"
      case statics.String.Boolean => "Boolean"
      case statics.String.Unit => "Unit"
      case statics.String.Any => "Any"
      case statics.String.List(generic) => s"List[$generic]"
    }
  }
}
