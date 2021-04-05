package io.zana.zapl.translator.program

import io.zana.zapl.structure.call.Callable
import io.zana.zapl.structure.comment.LineComment
import io.zana.zapl.structure.control.Control
import io.zana.zapl.structure.expression.{Call, Expression}
import io.zana.zapl.structure.variable.Assign
import io.zana.zapl.structure.module.Module
import io.zana.zapl.structure.function.Function
import io.zana.zapl.structure.variable.Variable
import io.zana.zapl.translator.Translatable
import io.zana.zapl.translator
import io.zana.zapl.{structure => structures}

object Program extends Translatable[structures.program.Program] {

  override def apply(structure: structures.program.Program): String = {
    val result = for {statement <- structure.statements} yield statement match {
      case e: LineComment => translator.comment.LineComment(e)
      case e: Module => translator.module.Module(e)
      case e: Callable => translator.call.Callable(e)
      case e: Function => translator.function.Function(e)
      case e: Variable => translator.variable.Variable(e)
      case e: Assign => translator.variable.Assign(e)
      case e: Control => translator.control.Control(e)
      case e => throw new Error(s"did not know how to parse ${e}")
    }

    "object Application extends App {\n" +
      result.mkString("\n") +
      "\n}"
  }
}
