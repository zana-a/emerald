package io.zana.zapl.test.parser.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.{primitive, statics}
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Variable extends Base {

  @Test
  def constant(): Unit = {
    Tester(
      Tools.Variable.parser,
      """
        |let a: Int = 2
        |""".stripMargin,
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        statics.Integer,
        primitive.Integer(2)
      )
    )
  }


  @Test
  def variable(): Unit = {
    Tester(
      Tools.Variable.parser,
      """
        |let mut a: Int = 2
        |""".stripMargin,
      Tools.Variable.structure(
        modifiable = true,
        Identifier("a"),
        statics.Integer,
        primitive.Integer(2)
      )
    )
  }

  @Test
  def reAssign(): Unit = {
    Tester(
      Tools.Assign.parser,
      """
        |a = 4
        |""".stripMargin,
      Tools.Assign.structure(
        Identifier("a"),
        primitive.Integer(4)
      )
    )
  }

  // TODO:
  // Test for:
  // 1. Primitives
  // 2. Function call
  // 3. Expressions

}
