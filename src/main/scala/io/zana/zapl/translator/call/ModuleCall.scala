package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object ModuleCall extends Translatable[structures.call.Module] {

  override def translate(structure: structures.call.Module): String = {

    val modules = {
      val identifiers = for {
        identifier <- structure.modules
      } yield translator.common.Identifier.translate(identifier)

      for {
        identifier <- identifiers
      } yield identifier
    }

    val function = translator.call.FunctionCall.translate(structure.caller)

    s"${modules.mkString(".")}.$function"
  }
}
