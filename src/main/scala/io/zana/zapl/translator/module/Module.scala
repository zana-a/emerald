package io.zana.zapl.translator.module

import io.zana.zapl.structure.module
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object Module extends Translatable[module.Module] {

  override def translate(structure: module.Module): String = {

    val name = translator.common.Identifier.translate(structure.name)

    s"""
       |mod $name do
       |
       |end
       |""".stripMargin
  }
}
