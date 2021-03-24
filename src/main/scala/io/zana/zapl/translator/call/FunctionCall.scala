package io.zana.zapl.translator.call

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object FunctionCall extends Translatable[structures.call.FunctionCall] {

  override def apply(structure: structures.call.FunctionCall): String = {
    val name = translator.identifier.Identifier(structure.name)

    val params = {
      val result = for {param <- structure.params} yield param match {
        case e: structures.identifier.Identifier =>
          translator.identifier.Identifier(e)
        case e: structures.primitive.Primitive =>
          translator.primitive.Primitive(e)
        case e: structures.call.FunctionCall =>
          apply(e)
        case e: structures.call.ModuleCall =>
          translator.call.ModuleCall(e)
        case e: structures.expression.Expression =>
          translator.expression.Expression.sanitise(
            translator.expression.Expression(e)
          )
        case e => throw new Error(s"translator not implemented for $e")
      }
      result.mkString(", ")
    }

    s"$name($params)"
  }
}
