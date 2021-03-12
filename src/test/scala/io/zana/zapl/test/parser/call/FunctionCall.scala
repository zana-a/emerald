package io.zana.zapl.test.parser.call

import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class FunctionCall extends Base {

  @Test
  def simple(): Unit = {
    Tester(
      Tools.FunctionCall.parser,
      "a()",
      Tools.FunctionCall.structure(
        Identifier("a"),
        List()
      )
    )
  }

  @Test
  def withParams(): Unit = {
    Tester(
      Tools.FunctionCall.parser,
      "a(true, false, \"string\", 123, [])",
      Tools.FunctionCall.structure(
        Identifier("a"),
        List(
          primitive.Boolean(true),
          primitive.Boolean(false),
          primitive.String("\"string\""),
          primitive.Integer(123),
          primitive.List(List()),
        )
      )
    )
  }

  // TODO:
  // 1. Add test for expression within params.

}
