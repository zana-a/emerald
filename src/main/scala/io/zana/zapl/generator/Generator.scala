package io.zana.zapl.generator

import io.zana.zapl.ast.Expression._
import io.zana.zapl.ast.Type

object Generator {

  object Generate {

    def definition(definition: Definition): String = {
      val result =
        definition.value match {
          case Type.Integer(i) => (
            Int
              .getClass
              .getName.
              toCharArray
              .dropRight(1)
              .mkString,
            i
          )
          case Type.Bool(i) => (
            Boolean
              .getClass
              .getName.
              toCharArray
              .dropRight(1)
              .mkString,
            i
          )
          case Type.String(i) => (
            "java.lang.String",
            "\"" + i + "\""
          )
        }

      s"var ${definition.identifier}: ${result._1} = ${result._2}"
    }
  }

}
