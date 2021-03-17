package io.zana.zapl.test.parser.program

import io.zana.zapl.structure.call
import io.zana.zapl.structure.comment.LineComment
import io.zana.zapl.structure.control.{Cond, Loop}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.function.Function
import io.zana.zapl.structure.statics
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.module.Module
import io.zana.zapl.structure.variable.{Assign, Variable}
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Program extends Base {

  @Test
  def lineComment(): Unit = {
    Tester(
      Tools.parser,
      """
        |# comment
        |""".stripMargin,
      Tools.structure(
        List(
          LineComment(" comment")
        )
      )
    )
  }

  @Test
  def module(): Unit = {
    Tester(
      Tools.parser,
      """
        |mod A do
        |
        |end
        |""".stripMargin,
      Tools.structure(
        List(
          Module(
            Identifier("A"),
            List()
          )
        )
      )
    )
  }

  @Test
  def callers(): Unit = {
    Tester(
      Tools.parser,
      """
        |a()
        |""".stripMargin,
      Tools.structure(
        List(
          call.FunctionCall(
            Identifier("a"),
            List()
          )
        )
      )
    )
    Tester(
      Tools.parser,
      """
        |A::a()
        |""".stripMargin,
      Tools.structure(
        List(
          call.ModuleCall(
            List(
              Identifier("A"),
            ),
            call.FunctionCall(
              Identifier("a"),
              List()
            )
          )
        )
      )
    )
  }

  @Test
  def function(): Unit = {
    Tester(
      Tools.parser,
      """
        |def a(): Int = 1
        |""".stripMargin,
      Tools.structure(
        List(
          Function(
            Identifier("a"),
            List(),
            statics.Integer,
            primitive.Integer(1)
          )
        )
      )
    )
  }

  @Test
  def variable(): Unit = {
    Tester(
      Tools.parser,
      """
        |let a: Int = 1
        |""".stripMargin,
      Tools.structure(
        List(
          Variable(
            modifiable = false,
            Identifier("a"),
            statics.Integer,
            primitive.Integer(1)
          )
        )
      )
    )
    Tester(
      Tools.parser,
      """
        |let mut a: Int = 1
        |""".stripMargin,
      Tools.structure(
        List(
          Variable(
            modifiable = true,
            Identifier("a"),
            statics.Integer,
            primitive.Integer(1)
          )
        )
      )
    )
    Tester(
      Tools.parser,
      """
        |a = 1
        |""".stripMargin,
      Tools.structure(
        List(
          Assign(
            Identifier("a"),
            primitive.Integer(1)
          )
        )
      )
    )
  }

  @Test
  def control(): Unit = {
    Tester(
      Tools.parser,
      """
        |cond do
        |
        |end
        |""".stripMargin,
      Tools.structure(
        List(
          Cond(
            None,
            None
          )
        )
      )
    )
    Tester(
      Tools.parser,
      """
        |loop do
        |
        |end
        |""".stripMargin,
      Tools.structure(
        List(
          Loop(
            None,
            None
          )
        )
      )
    )
  }
}
