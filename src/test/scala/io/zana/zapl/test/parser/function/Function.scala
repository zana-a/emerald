package io.zana.zapl.test.parser.function

import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.statics
import io.zana.zapl.structure.call
import io.zana.zapl.structure.primitive
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Function extends Base {

  @Test
  def simple(): Unit = {
    Tester(
      Tools.Function.parse,
      "def f(): String = \"\"",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.String,
        primitive.String("\"\"")
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): Boolean = true",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.Boolean,
        primitive.Boolean(true)
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): List<Any> = [true]",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.List(statics.Any),
        primitive.List(List(primitive.Boolean(true)))
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): Any = true",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.Any,
        primitive.Boolean(true)
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): Int = 1",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.Integer,
        primitive.Integer(1)
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): Int = a()",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.Integer,
        call.FunctionCall(
          Identifier("a"),
          List()
        )
      )
    )
    Tester(
      Tools.Function.parse,
      "def f(): Int = A::a()",
      Tools.Function.structure(
        Identifier("f"),
        List(),
        statics.Integer,
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
    )
  }

  //TODO:
  // 1. def with expression
  // 2. def with block
  // 2. def with control
  // 2. def with block
}
