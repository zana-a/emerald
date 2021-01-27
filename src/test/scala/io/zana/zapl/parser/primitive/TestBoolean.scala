package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestBoolean {

  def testBoolean(input: String, expected: structure.primitive.Type) = {
    parser.Base.parse(parser.Primitive.boolean, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testTrue = {
    val subject =
      """
        |true
        |"""
        .stripMargin
        .trim

    testBoolean(subject, structure.primitive.Boolean(true))
  }

  @Test
  def testFalse = {
    val subject =
      """
        |false
        |"""
        .stripMargin
        .trim

    testBoolean(subject, structure.primitive.Boolean(false))
  }
}
