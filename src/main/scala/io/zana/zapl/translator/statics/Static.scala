package io.zana.zapl.translator.statics

import io.zana.zapl.structure.statics
import io.zana.zapl.translator.Translatable

object Static extends Translatable[statics.Static] {

  override def apply(structure: statics.Static): String = {
    structure match {
      case statics.Integer => "Predef.Int"
      case statics.String => "Predef.String"
      case statics.Boolean => "Predef.Boolean"
      case statics.Any => "Any"
      case statics.List(e) => s"List[${apply(e)}]"
    }
  }
}
