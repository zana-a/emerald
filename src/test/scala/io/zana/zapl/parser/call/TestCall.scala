package io.zana.zapl.parser.call

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestCall {
  def testCall(input: String, expected: List[Any]) = {
    parser.Base.parse(parser.program.Program.build, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testFunctionCall = {
    val subject =
      """
        |f()
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        structure.call.function.Function(
          structure.common.Identifier("f"),
          List(),
        )
      )
    )
  }

  @Test
  def testFunctionCallWithParams = {
    val subject =
      """
        |f(1)
        |f(true)
        |f(1 + 1)
        |f(a)
        |f(inner())
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        structure.call.function.Function(
          structure.common.Identifier("f"),
          List(
            structure.primitive.Integer(1)
          ),
        ),
        structure.call.function.Function(
          structure.common.Identifier("f"),
          List(
            structure.primitive.Boolean(true)
          ),
        ),
        //        structure.call.function.Function(
        //          structure.common.Identifier("f"),
        //          List(
        //            TODO Expression here
        //          ),
        //        ),
        structure.call.function.Function(
          structure.common.Identifier("f"),
          List(
            structure.call.function.Function(
              structure.common.Identifier("f"),
              List(),
            )
          ),
        )
      )
    )
  }
}
