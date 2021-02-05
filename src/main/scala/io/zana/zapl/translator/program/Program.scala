package io.zana.zapl.translator.program

import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structs}

object Program extends Translatable[structs.program.Program] {
  private def helper(structure: structs.program.Program): List[String] = {
    for {
      statement <- structure.statements
    } yield statement match {
      case structs.variable.Variable(_, _) =>
        translator.variable.Variable
          .translate(statement.asInstanceOf[structs.variable.Variable])
    }
  }

  override def translate(structure: structs.program.Program): String = {
    val result = for {
      i <- helper(structure)
    } yield i ++ "\n"

    result.mkString
  }
}
