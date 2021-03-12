package io.zana.zapl.translator.call

import io.zana.zapl.structure.call.FunctionCall
import io.zana.zapl.structure.call.ModuleCall
import io.zana.zapl.structure.call
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object Callable extends Translatable[call.Callable] {

  override def apply(structure: call.Callable): String = {
    structure match {
      case e: FunctionCall => translator.call.FunctionCall(e)
      case e: ModuleCall => translator.call.ModuleCall(e)
    }
  }

}
