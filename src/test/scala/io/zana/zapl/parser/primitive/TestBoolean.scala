package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestBoolean {

  @Test
  def testTrue(): Unit = {
    val subject =
      """
        |true
        |"""
        .stripMargin
        .trim

    testBoolean(subject, structure.primitive.Boolean(true))
  }

  @Test
  def testFalse(): Unit = {
    val subject =
      """
        |false
        |"""
        .stripMargin
        .trim

    testBoolean(subject, structure.primitive.Boolean(false))
  }

  def testBoolean(input: String, expected: structure.primitive.Type): Unit = {
    parser.Base.parse(Primitive.boolean, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }
}
