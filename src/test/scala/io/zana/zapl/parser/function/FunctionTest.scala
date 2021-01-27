package io.zana.zapl.parser.function

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class FunctionTest {

  def testFunction(input: String, expected: structure.Function) = {
    parser.Base.parse(parser.Function.function, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

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
      structure.Function(
        structure.Identifier("integer"),
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
      structure.Function(
        structure.Identifier("string"),
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
      structure.Function(
        structure.Identifier("boolean"),
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
      structure.Function(
        structure.Identifier("list"),
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
      structure.Function(
        structure.Identifier("block"),
        List(),
        structure.Block(List())
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
      structure.Function(
        structure.Identifier("f"),
        List(),
        structure.Identifier("some_id")
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
      structure.Function(
        structure.Identifier("f"),
        List(
          structure.Identifier("one")
        ),
        structure.primitive.List(List())
      ),
    )
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
      structure.Function(
        structure.Identifier("f"),
        List(
          structure.Identifier("one"),
          structure.Identifier("two"),
          structure.Identifier("three"),
        ),
        structure.primitive.List(List())
      ),
    )
  }
}
