package io.zana.zapl.translator.statics

import io.zana.zapl.structure.statics
import io.zana.zapl.translator.Translatable

object Static extends Translatable[statics.Static] {

  override def apply(structure: statics.Static): String = {
    structure match {
      case statics.Integer => Int.getClass.getName.dropRight(1)
      case statics.String => "java.lang.String"
      case statics.Boolean => Boolean.getClass.getName.dropRight(1)
      case statics.Any => "Any"
      case statics.List(e) => s"List[${apply(e)}]"
    }
  }
}
