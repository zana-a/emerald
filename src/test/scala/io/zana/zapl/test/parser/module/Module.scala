package io.zana.zapl.test.parser.module

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.control.{Cond, Loop}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.function.Function
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.statics
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Module extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      Tools.parser,
      """
        |mod A do
        |end
        |""".stripMargin,
      Tools.structure(
        Identifier("A"),
        List()
      )
    )
  }

  @Test
  def withFunction(): Unit = {
    Tester(
      Tools.parser,
      """
        |mod A do
        | def f(): Int = 1
        |
        | def f(): Any = do
        | end
        |
        | def f(): Any = cond do
        | end
        |
        | def f(): Any = loop do
        | end
        |end
        |""".stripMargin,
      Tools.structure(
        Identifier("A"),
        List(
          Function(
            Identifier("f"),
            List(),
            statics.Integer,
            primitive.Integer(1)
          ),
          Function(
            Identifier("f"),
            List(),
            statics.Any,
            Block(List())
          ),
          Function(
            Identifier("f"),
            List(),
            statics.Any,
            Cond(
              None,
              None
            )
          ),
          Function(
            Identifier("f"),
            List(),
            statics.Any,
            Loop(
              None,
              None
            )
          )
        )
      )
    )
  }

  @Test
  def withNestedModule(): Unit = {
    Tester(
      Tools.parser,
      """
        |mod A do
        |
        |  mod B do
        |
        |    mod C do
        |
        |    end
        |
        |  end
        |
        |end
        |""".stripMargin,
      Tools.structure(
        Identifier("A"),
        List(
          Tools.structure(
            Identifier("B"),
            List(
              Tools.structure(
                Identifier("C"),
                List()
              )
            )
          )
        )
      )
    )
  }
}
