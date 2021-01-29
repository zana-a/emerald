package io.zana.zapl.parser.function

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.{common, function}
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestFunction {

  @Test
  def testPrimitiveFunction = {
    val subjectInteger =
      """
        |def integer() = 1
        |"""
        .stripMargin
        .trim

    testFunction(
      subjectInteger,
      function.Function(
        Identifier("integer"),
        List(),
        structure.primitive.Integer(1)
      ),
    )

    val subjectString =
      """
        |def string() = "hello!"
        |"""
        .stripMargin
        .trim

    testFunction(
      subjectString,
      function.Function(
        common.Identifier("string"),
        List(),
        structure.primitive.String("\"hello!\"")
      ),
    )

    val subjectBoolean =
      """
        |def boolean() = true
        |"""
        .stripMargin
        .trim

    testFunction(
      subjectBoolean,
      function.Function(
        common.Identifier("boolean"),
        List(),
        structure.primitive.Boolean(true)
      ),
    )

    val subjectList =
      """
        |def list() = []
        |"""
        .stripMargin
        .trim

    testFunction(
      subjectList,
      function.Function(
        common.Identifier("list"),
        List(),
        structure.primitive.List(List())
      ),
    )
  }

  @Test
  def testBlockFunction = {
    val subject =
      """
        |def block() = do
        |
        |end
        |"""
        .stripMargin
        .trim

    testFunction(
      subject,
      function.Function(
        common.Identifier("block"),
        List(),
        Block(List())
      ),
    )
  }

  @Test
  def testIdentiferFunction = {
    val subject =
      """
        |def f() = some_id
        |"""
        .stripMargin
        .trim

    testFunction(
      subject,
      function.Function(
        common.Identifier("f"),
        List(),
        common.Identifier("some_id")
      ),
    )
  }

  @Test
  def testOneParamFunction = {
    val subject =
      """
        |def f(one) = []
        |"""
        .stripMargin
        .trim

    testFunction(
      subject,
      function.Function(
        common.Identifier("f"),
        List(
          common.Identifier("one")
        ),
        structure.primitive.List(List())
      ),
    )
  }

  def testFunction(input: String, expected: function.Function) = {
    parser.Base.parse(parser.function.Function.function, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testMultiParamFunction = {
    val subject =
      """
        |def f(one, two, three) = []
        |"""
        .stripMargin
        .trim

    testFunction(
      subject,
      function.Function(
        common.Identifier("f"),
        List(
          common.Identifier("one"),
          common.Identifier("two"),
          common.Identifier("three"),
        ),
        structure.primitive.List(List())
      ),
    )
  }
}
