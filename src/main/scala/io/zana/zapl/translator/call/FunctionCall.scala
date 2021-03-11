package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object FunctionCall extends Translatable[structures.call.Function] {

  override def apply(structure: structures.call.Function): String = {
    val name = translator.common.Identifier
      .apply(structure.name)

    val params = for {
      param <- structure.params
    } yield param match {
      case identifier: structures.identifier.Identifier =>
        translator.common.Identifier.apply(identifier)

      case primitive: structures.primitive.Primitive =>
        translator.primitive.Primitive.apply(primitive)

      case functionCall: structures.call.Function =>
        apply(functionCall)

      case moduleCall: structures.call.Module =>
        translator.call.ModuleCall.apply(moduleCall)

      case e => s"??? translator not implemented for $e"
    }

    s"$name(${params.mkString(", ")})" ++ "\n"
  }
}
