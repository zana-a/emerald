package io.zana.zapl.test.parser.variable

import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.call
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

  @Test
  def static(): Unit = {
    Tester(
      Tools.Variable.parser,
      "let a: Int = 1",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        statics.Integer,
        primitive.Integer(1)
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: Boolean = true",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        statics.Boolean,
        primitive.Boolean(true)
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: String = \"\"",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        statics.String,
        primitive.String("\"\"")
      )
    )
    Tester(
      Tools.Variable.parser,
      "let a: List<Int> = [1]",
      Tools.Variable.structure(
        modifiable = false,
        Identifier("a"),
        statics.List(statics.Integer),
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
        statics.List(statics.Boolean),
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
        statics.List(statics.String),
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
        statics.List(statics.List(statics.Integer)),
        primitive.List(List(
          primitive.List(List(
            primitive.Integer(1)
          ))
        ))
      )
    )
  }

  def expression(): Unit = {
    Tester(
      Tools.Variable.parser,
      "let a: List<Any> = function()",
      Tools.Assign.structure(
        Identifier("a"),
        call.Function(
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
        call.Module(
          List(
            Identifier("A"),
          ),
          call.Function(
            Identifier("function"),
            List()
          )
        )
      )
    )
  }

  // TODO:
  // Test for:
  // 1. Primitives                  | done
  // 2. Expressions + Function call | incomplete - done
  // 3. Static                      | done

}