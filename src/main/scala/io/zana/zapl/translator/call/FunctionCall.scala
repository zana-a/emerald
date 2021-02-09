package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object FunctionCall extends Translatable[structures.call.FunctionCall] {

  override def translate(structure: structures.call.FunctionCall): String = {
    val name = translator.common.Identifier
      .translate(structure.name)

    val params = for {
      param <- structure.params
    } yield param match {
      case identifier: structures.common.Identifier =>
        translator.common.Identifier.translate(identifier)

      case primitive: structures.primitive.Primitive =>
        translator.primitive.Primitive.translate(primitive)

      case functionCall: structures.call.FunctionCall =>
        translate(functionCall)

      case moduleCall: structures.call.ModuleCall =>
        translator.call.ModuleCall.translate(moduleCall)

      case e => s"??? translator not implemented for $e"
    }

    s"$name(${params.mkString(", ")})" ++ "\n"
  }
}
