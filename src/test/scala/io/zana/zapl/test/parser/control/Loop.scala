package io.zana.zapl.test.parser.control

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.control.Arm
import io.zana.zapl.structure.expression.Pair
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.{call, control, primitive}
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Loop extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      Tools.Loop.parser,
      """
        |loop do
        |
        |end
        |""".stripMargin,
      control.Loop(
        None,
        None,
      )
    )
  }

  @Test
  def arms(): Unit = {
    Tester(
      Tools.Loop.parser,
      """
        |loop do
        |  true => false
        |  false => true
        |  true && false => true
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        | _ => true
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        | _ => 1 + 1
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        | _ => 1 == 1
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        |  true => false
        |  false => true
        |  true && false => true
        |  _ => false
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        |  _ => a
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        |  _ => a()
        |end
        |""".stripMargin,
      control.Loop(
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
      Tools.Loop.parser,
      """
        |loop do
        |  _ => A::a()
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => true
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => 1
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => "demo"
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => []
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => do
        |
        |  end
        |end
        |""".stripMargin,
      control.Loop(
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
        |loop do
        |  _ => cond do
        |  end
        |end
        |""".stripMargin,
      control.Loop(
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
    Tester(
      parser,
      """
        |loop do
        |  _ => loop do
        |  end
        |end
        |""".stripMargin,
      control.Loop(
        None,
        Some(
          Arm(
            primitive.Boolean(true),
            control.Loop(
              None,
              None
            )
          )
        ),
      )
    )
  }
}

