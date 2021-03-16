package io.zana.zapl.test.parser.control

import io.zana.zapl.structure.control
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.control.Arm
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Cond extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |
        |end
        |""".stripMargin,
      control.Cond(
        None,
        None,
      )
    )
  }

  @Test
  def defaultArm(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        | _ => true
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.Boolean(true)
          )
        )
      )
    )
  }
}
