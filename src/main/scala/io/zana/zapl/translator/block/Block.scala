package io.zana.zapl.translator.block

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{structure => structures}

object Block extends Translatable[structures.block.Block] {

  override def translate(structure: structures.block.Block): String = {
    val body = for {
      items <- structure.body
    } yield items

    s"""
       |{
       |$body
       |}
       |""".stripMargin
  }
}
