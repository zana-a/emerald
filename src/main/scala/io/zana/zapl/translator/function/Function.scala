package io.zana.zapl.translator.function

import io.zana.zapl.structure.function
import io.zana.zapl.translator.{Translatable, common}

object Function extends Translatable[function.Function] {

  override def translate(structure: function.Function): String = {
    val name = common.Identifier.translate(structure.name)

    val params = for {
      param <- structure.params
    } yield param.name ++ ": Any"

    val body = structure.body

    s"""
       |def $name(${params.mkString(", ")}) = {
       |  $body
       |}
       |""".stripMargin
  }
}
