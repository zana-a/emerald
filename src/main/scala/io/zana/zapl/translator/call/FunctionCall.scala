package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object FunctionCall extends Translatable[structures.call.FunctionCall] {

  override def apply(structure: structures.call.FunctionCall): String = {
    val name = translator.identifier.Identifier
      .apply(structure.name)

    val params = for {
      param <- structure.params
    } yield param match {
      case identifier: structures.identifier.Identifier =>
        translator.identifier.Identifier.apply(identifier)

      case primitive: structures.primitive.Primitive =>
        translator.primitive.Primitive.apply(primitive)

      case functionCall: structures.call.FunctionCall =>
        apply(functionCall)

      case moduleCall: structures.call.ModuleCall =>
        translator.call.ModuleCall.apply(moduleCall)

      case e => s"??? translator not implemented for $e"
    }

    s"$name(${params.mkString(", ")})"
  }
}
