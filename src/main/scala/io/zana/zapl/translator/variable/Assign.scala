package io.zana.zapl.translator.variable

import io.zana.zapl.structure.call.Callable
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator
import io.zana.zapl.{structure => structures}
import io.zana.zapl.translator.Translatable

object Assign extends Translatable[structures.variable.Assign] {

  //    Expression.apply | Primitive.apply | Call.apply


  override def apply(structure: structures.variable.Assign): String = {
    val body = structure.body match {
      case e: Expression => translator.expression.Expression(e)
      case e: Primitive => translator.primitive.Primitive(e)
      case e: Callable => translator.call.Callable(e)
      case e => throw new Error(s"did not know how to parse $e")
    }

    val id = translator.identifier.Identifier(structure.name)

    s"$id = $body"
  }
}
