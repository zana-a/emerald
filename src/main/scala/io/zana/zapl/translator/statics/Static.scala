package io.zana.zapl.translator.statics

import io.zana.zapl.translator.Translatable
import io.zana.zapl.structure.statics

object Static extends Translatable[statics.Static.Static]{

  override def translate(structure: statics.Static.Static): String = {
    structure match {
      case statics.Static.Int => "Int"
      case statics.Static.String => "String"
      case statics.Static.Boolean => "Boolean"
      case statics.Static.Unit => "Unit"
      case statics.Static.Any => "Any"
      case statics.Static.List(generic) => s"List[$generic]"
    }
  }
}
