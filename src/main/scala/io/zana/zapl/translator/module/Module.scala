package io.zana.zapl.translator.module

import io.zana.zapl.structure.comment.LineComment
import io.zana.zapl.structure.function.Function
import io.zana.zapl.structure.module
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Module extends Translatable[module.Module] {

  override def apply(structure: module.Module): String = {

    val name = translator.identifier.Identifier.apply(structure.name)

    val body = {
      val gen = for {item <- structure.body} yield item match {
        case e: LineComment => translator.comment.LineComment(e)
        case e: Function => translator.function.Function(e)
        case e: structures.module.Module => apply(e)
        case e => throw new Error(s"Did not know how to parse $e")
      }

      for {head <- gen} yield head
    }

    s"object $name {\n${body.mkString("\n")}\n}"
  }
}
