package io.zana.zapl.translator.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.{primitive, variable}
import io.zana.zapl.translator.Translatable
import io.zana.zapl.translator.primitive.{Primitive => PrimitiveTranslator}

object Variable extends Translatable[variable.Variable] {

  override def translate(structure: variable.Variable): String = {
    val identifier = structure.name match {
      case Identifier(name) => name
    }

    //    todo:  call,  expression

    val body = structure.body match {
      case primitive.String(_) =>
        PrimitiveTranslator
          .translate(structure
            .body
            .asInstanceOf[primitive.Type]
          )

      case primitive.Integer(_) =>
        PrimitiveTranslator
          .translate(structure
            .body
            .asInstanceOf[primitive.Type]
          )

      case primitive.Boolean(_) =>
        PrimitiveTranslator
          .translate(structure
            .body
            .asInstanceOf[primitive.Type]
          )

      case primitive.List(_) =>
        PrimitiveTranslator
          .translate(structure
            .body
            .asInstanceOf[primitive.Type]
          )
    }

    s"var $identifier = $body"
  }
}
