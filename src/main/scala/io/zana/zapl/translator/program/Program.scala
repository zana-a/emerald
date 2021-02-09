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

      case functionCall: structures.call.FunctionCall =>
        translator.call.FunctionCall
          .translate(functionCall)

      case moduleCall: structures.call.ModuleCall =>
        translator.call.ModuleCall
          .translate(moduleCall)

      case e => s"??? translator not implemented for $e"
    }
  }

  override def translate(structure: structures.program.Program): String = {
    val result = for {i <- helper(structure)} yield i

    //todo replace object name with file name possibly
    s"""
       |object Application extends App {
       |${result.mkString("\n")}
       |}
       |"""
      .stripMargin
  }
}
