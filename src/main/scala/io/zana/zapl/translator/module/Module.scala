package io.zana.zapl.translator.module

import io.zana.zapl.structure.{comment, function, module}
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object Module extends Translatable[module.Module] {

  override def translate(structure: module.Module): String = {

    val name = translator.common.Identifier.translate(structure.name)
    val body = for {item <- structure.body} yield item match {
      case comment.LineComment(_) =>
        translator.comment.LineComment
          .translate(item.asInstanceOf[comment.LineComment])

      case function.Function(_, _, _) =>
        translator.function.Function
          .translate(item.asInstanceOf[function.Function])

      case module.Module(_, _) =>
        translate(item.asInstanceOf[module.Module])
    }

    s"""
       |object $name {
       |  $body
       |}
       |""".stripMargin
  }
}
