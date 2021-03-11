package io.zana.zapl.translator.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.{primitive, variable}
import io.zana.zapl.translator.Translatable
import io.zana.zapl.translator.primitive.{Primitive => PrimitiveTranslator}

object Variable extends Translatable[variable.Variable] {

  override def apply(structure: variable.Variable): String = {
    val identifier = structure.name match {
      case Identifier(name) => name
    }

    //    todo:  call,  expression

    val body = structure.body match {
      case primitive.String(_) =>
        PrimitiveTranslator
          .apply(structure
            .body
            .asInstanceOf[primitive.Primitive]
          )

      case primitive.Integer(_) =>
        PrimitiveTranslator
          .apply(structure
            .body
            .asInstanceOf[primitive.Primitive]
          )

      case primitive.Boolean(_) =>
        PrimitiveTranslator
          .apply(structure
            .body
            .asInstanceOf[primitive.Primitive]
          )

      case primitive.List(_) =>
        PrimitiveTranslator
          .apply(structure
            .body
            .asInstanceOf[primitive.Primitive]
          )
    }

    s"var $identifier = $body"
  }
}
