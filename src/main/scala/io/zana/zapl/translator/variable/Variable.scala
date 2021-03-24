package io.zana.zapl.translator.variable

import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.structure.variable
import io.zana.zapl.structure.call.Callable
import io.zana.zapl.translator.Translatable
import io.zana.zapl.translator

object Variable extends Translatable[variable.Variable] {

  override def apply(structure: variable.Variable): String = {
    val identifier = translator.identifier.Identifier(structure.name)

    val body = structure.body match {
      case e: Primitive => translator.primitive.Primitive(e)
      case e: Callable => translator.call.Callable(e)
      case e: Expression => translator.expression.Expression(e)
      case e => throw new Error(e.toString)
    }

    val modifier = if (structure.modifiable) "var" else "val"

    s"$modifier $identifier = $body"
  }
}

//Expression.apply | Primitive.apply | Call.apply
