package io.zana.zapl.translator.function

import io.zana.zapl.parser.Base.Keyword
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Function extends Translatable[structures.function.Function] {

  override def translate(structure: structures.function.Function): String = {
    val name = translator.common.Identifier.translate(structure.name)

    val param = for {
      param <- structure.params
    } yield s"${
      translator
        .common
        .Identifier
        .translate(param.name)
    }: ${param.static}"

    val `return` = structure.`return`

    val body: String = structure.body match {
      case primitive: Primitive =>
        translator.primitive.Primitive.translate(primitive)
      //      case call: Callable =>
      //        translator.primitive.Primitive.translate(primitive)
      case e => s"??? not implemented for $e"
    }

    s"""
       |def $name(${param.mkString(Keyword.COMMA)}): ${`return`} = {
       |  $body
       |}
       |""".stripMargin
  }
}
