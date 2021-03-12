package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object ModuleCall extends Translatable[structures.call.ModuleCall] {

  override def apply(structure: structures.call.ModuleCall): String = {

    val modules = {
      val identifiers = for {
        identifier <- structure.modules
      } yield translator.identifier.Identifier.apply(identifier)

      for {
        identifier <- identifiers
      } yield identifier
    }

    val function = translator.call.FunctionCall.apply(structure.caller)

    s"${modules.mkString(".")}.$function"
  }
}
