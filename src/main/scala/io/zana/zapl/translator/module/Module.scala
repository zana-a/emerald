package io.zana.zapl.translator.module

import io.zana.zapl.structure.module
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Module extends Translatable[module.Module] {

  override def apply(structure: module.Module): String = {

    val name = translator.identifier.Identifier.apply(structure.name)

    val body = {
      val gen = for {
        item <- structure.body
      } yield item match {
        case comment: structures.comment.LineComment =>
          translator.comment.LineComment
            .apply(comment)

        case function: structures.function.Function =>
          translator.function.Function
            .apply(function)

        case module: structures.module.Module =>
          apply(module)

        case e => s"??? translator not implemented for $e"
      }

      for {
        head <- gen
      } yield head
    }
    s"""
       |object $name {
       |${body.mkString("\n")}
       |}
       |""".stripMargin
  }
}
