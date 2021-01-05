package io.zana.zapl.translator

import io.zana.zapl.structure.Type.Type
import io.zana.zapl.structure.Variable

object Translator {

  // TODO: incomplete function
  def `type`(`type`: Type[_]): String = {
    `type`.value.toString
  }

  // TODO: incomplete function
  def variable(variable: Variable): String = {
    s"val ${variable.name} = ${variable.body}"
  }
}
