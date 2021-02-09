package io.zana.zapl.translator.program

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Program extends Translatable[structures.program.Program] {
  private def helper(structure: structures.program.Program): List[String] = {
    for {
      statement <- structure.statements
    } yield statement match {
      case variable: structures.variable.Variable =>
        translator.variable.Variable
          .translate(variable)

      case module: structures.module.Module =>
        translator.module.Module
          .translate(module)

      case function: structures.function.Function =>
        translator.function.Function
          .translate(function)
    }
  }

  override def translate(structure: structures.program.Program): String = {
    val result = for {i <- helper(structure)} yield i
    result.mkString("\n")
  }
}
