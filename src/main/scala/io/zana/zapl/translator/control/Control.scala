package io.zana.zapl.translator.control

import io.zana.zapl.structure.control.Cond
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Control extends Translatable[structures.control.Control] {
  override def apply(structure: structures.control.Control): String =
    structure match {
      case e: Cond => translator.control.Cond(e)
      //      case e: Loop => translator.control.Loop(e)
      case e => throw new Error(s"did not know how to translate ${e}")
    }
}
