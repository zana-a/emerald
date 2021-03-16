package io.zana.zapl.test.parser.control

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

  // TODO:
  // 1. command an expression | done
  // 2. command an identifier |
  // 3. command a primitive
  // 4. command a block
  // 5. command a control
  // 6. command a call
}
