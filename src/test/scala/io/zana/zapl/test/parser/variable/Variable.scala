package io.zana.zapl.test.parser.variable

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.expression.Pair
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.{call, primitive, statics}
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
        Some(statics.Integer),
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
        Some(statics.Integer),
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

  @Test
  def static(): Unit = {
    Tester(
      Tools.Variable.parser,
      "let a: Int = 1",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.Integer),
        primitive.Integer(1)
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: Boolean = true",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.Boolean),
        primitive.Boolean(true)
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: String = \"\"",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.String),
        primitive.String("\"\"")
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: List<Int> = [1]",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.List(statics.Integer)),
        primitive.List(List(
          primitive.Integer(1)
        ))
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: List<Boolean> = [true]",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.List(statics.Boolean)),
        primitive.List(List(
          primitive.Boolean(true)
        ))
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: List<String> = [\"\"]",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.List(statics.String)),
        primitive.List(List(
          primitive.String("\"\"")
        ))
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: List<List<Int>> = [[1]]",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.List(statics.List(statics.Integer))),
        primitive.List(List(
          primitive.List(List(
            primitive.Integer(1)
          ))
        ))
      )
    )
  }

  def caller(): Unit = {
    Tester(
      Tools.Variable.parser,
      "let a: List<Any> = function()",
      Tools.Assign.structure(
        Identifier("a"),
        call.FunctionCall(
          Identifier("function"),
          List()
        )
      )
    )
    Tester(
      Tools.Variable.parser,
      "a = A::function()",
      Tools.Assign.structure(
        Identifier("a"),
        call.ModuleCall(
          List(
            Identifier("A"),
          ),
          call.FunctionCall(
            Identifier("function"),
            List()
          )
        )
      )
    )
  }

  @Test
  def expression(): Unit = {
    Tester(
      Tools.Variable.parser,
      "let a: List<Any> = 1 + 2",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        Some(statics.List(statics.Any)),
        Pair(
          PLUS,
          primitive.Integer(1),
          primitive.Integer(2)
        )
      )
    )
    Tester(
      Tools.Assign.parser,
      "a = 1 + 2",
      Tools.Assign.structure(
        Identifier("a"),
        Pair(
          PLUS,
          primitive.Integer(1),
          primitive.Integer(2)
        )
      )
    )
  }
}
