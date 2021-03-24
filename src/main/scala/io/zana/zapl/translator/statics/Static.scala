package io.zana.zapl.translator.statics

import io.zana.zapl.structure.statics
import io.zana.zapl.translator.Translatable

object Static extends Translatable[statics.Static] {

  def sanitise(s: String, n: Int): String = s.dropRight(1)

  override def apply(structure: statics.Static): String = {
    structure match {
      case statics.Integer => sanitise(Int.getClass.getName, 1)
      case statics.String => "java.lang.String"
      case statics.Boolean => sanitise(Boolean.getClass.getName, 1)
      case statics.Any => "Any"
      case statics.List(generic) => s"List[$generic]"
    }
  }
}
