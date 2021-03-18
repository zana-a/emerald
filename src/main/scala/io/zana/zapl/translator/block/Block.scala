package io.zana.zapl.translator.block

import io.zana.zapl.structure.call.Callable
import io.zana.zapl.structure.comment.LineComment
import io.zana.zapl.structure.control.Control
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.variable.Variable
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{structure => structures, translator}

object Block extends Translatable[structures.block.Block] {

  override def apply(structure: structures.block.Block): String = {
    val body = for {item <- structure.body} yield item match {
      case e: LineComment => translator.comment.LineComment(e)
      case e: Variable => translator.variable.Variable(e)
      //      case e: Assign => translator.Assign.Assign(e
      case e: Callable => translator.call.Callable(e)
      case e: Expression => translator.expression.Expression(e)
      //      case e: Control => translator.control.Control(e
    }

    s"{\n${body.mkString("\n")}\n}"
  }
}
