package io.zana.zapl.translator.control

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{structure => structures}
import io.zana.zapl.translator

object Control extends Translatable[structures.control.Control] {
  override def apply(structure: structures.control.Control): String =
    structure match {
      case e: structures.call.ModuleCall => translator.call.ModuleCall(e)
      case e: structures.call.FunctionCall => translator.call.FunctionCall(e)
      case e => throw new Error(s"did not know how to translate ${e}")
    }
}
