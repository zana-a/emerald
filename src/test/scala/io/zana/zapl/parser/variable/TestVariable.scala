package io.zana.zapl.parser.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.variable.Variable
import io.zana.zapl.structure.{common, variable}
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestVariable {

  @Test
  def testStringVariable(): Unit = {
    val subject =
      """
        |a = "hello!"
        |"""
        .stripMargin
        .trim

    testVariable(
      subject,
      variable.Variable(
        Identifier("a"),
        structure.primitive.String("\"hello!\"")
      )
    )
  }

  @Test
  def testIntegerVariable(): Unit = {
    val subject =
      """
        |a = 1
        |"""
        .stripMargin
        .trim

    testVariable(
      subject,
      variable.Variable(
        common.Identifier("a"),
        structure.primitive.Integer(1)
      )
    )
  }

  @Test
  def testBooleanVariable(): Unit = {
    val subject =
      """
        |a = true
        |"""
        .stripMargin
        .trim

    testVariable(
      subject,
      variable.Variable(
        common.Identifier("a"),
        structure.primitive.Boolean(true)
      )
    )
  }

  def testVariable(input: String, expected: Variable): Unit = {
    parser.Base.parse(parser.variable.Variable.variable, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testListVariable(): Unit = {
    val subject =
      """
        |a = ["apple", 12, true, ["banana", 1]]
        |"""
        .stripMargin
        .trim

    testVariable(
      subject,
      variable.Variable(
        common.Identifier("a"),
        structure.primitive.List(List(
          structure.primitive.String("\"apple\""),
          structure.primitive.Integer(12),
          structure.primitive.Boolean(true),
          structure.primitive.List(List(
            structure.primitive.String("\"banana\""),
            structure.primitive.Integer(1)
          ))
        ))
      )
    )
  }
}
