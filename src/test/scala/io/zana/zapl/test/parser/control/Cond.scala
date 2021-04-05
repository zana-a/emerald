package io.zana.zapl.test.parser.control

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.control
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.control.Arm
import io.zana.zapl.structure.expression.Pair
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.call
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
  def arms(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |  true => false
        |  false => true
        |  true && false => true
        |end
        |""".stripMargin,
      control.Cond(
        Some(
          List(
            Arm(
              primitive.Boolean(true),
              primitive.Boolean(false),
            ),
            Arm(
              primitive.Boolean(false),
              primitive.Boolean(true),
            ),
            Arm(
              Pair("&&", primitive.Boolean(true), primitive.Boolean(false)),
              primitive.Boolean(true),
            )
          )
        ),
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
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        | _ => 1 + 1
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            Pair("+", primitive.Integer(1), primitive.Integer(1))
          )
        )
      )
    )
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        | _ => 1 == 1
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            Pair("==", primitive.Integer(1), primitive.Integer(1))
          )
        )
      )
    )
  }

  @Test
  def multiArm(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |  true => false
        |  false => true
        |  true && false => true
        |  _ => false
        |end
        |""".stripMargin,
      control.Cond(
        Some(
          List(
            Arm(
              primitive.Boolean(true),
              primitive.Boolean(false),
            ),
            Arm(
              primitive.Boolean(false),
              primitive.Boolean(true),
            ),
            Arm(
              Pair("&&", primitive.Boolean(true), primitive.Boolean(false)),
              primitive.Boolean(true),
            )
          )
        ),
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.Boolean(false),
          )
        ),
      )
    )
  }

  @Test
  def identifier(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |  _ => a
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            Identifier("a")
          )
        ),
      )
    )
  }

  @Test
  def callers(): Unit = {
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |  _ => a()
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            call.FunctionCall(
              Identifier("a"),
              List()
            )
          )
        ),
      )
    )
    Tester(
      Tools.Cond.parser,
      """
        |cond do
        |  _ => A::a()
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            call.ModuleCall(
              List(
                Identifier("A")
              ),
              call.FunctionCall(
                Identifier("a"),
                List()
              )
            )
          )
        ),
      )
    )
  }

  @Test
  def primitives(): Unit = {
    Tester(
      parser,
      """
        |cond do
        |  _ => true
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.Boolean(true)
          )
        ),
      )
    )
    Tester(
      parser,
      """
        |cond do
        |  _ => 1
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.Integer(1)
          )
        ),
      )
    )
    Tester(
      parser,
      """
        |cond do
        |  _ => "demo"
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.String("\"demo\"")
          )
        ),
      )
    )
    Tester(
      parser,
      """
        |cond do
        |  _ => []
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            primitive.List(List())
          )
        ),
      )
    )
  }

  @Test
  def block(): Unit = {
    Tester(
      parser,
      """
        |cond do
        |  _ => do
        |
        |  end
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            Block(List())
          )
        ),
      )
    )
  }

  @Test
  def controls(): Unit = {
    Tester(
      parser,
      """
        |cond do
        |  _ => cond do
        |  end
        |end
        |""".stripMargin,
      control.Cond(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            control.Cond(
              None,
              None
            )
          )
        ),
      )
    )
  }
}
