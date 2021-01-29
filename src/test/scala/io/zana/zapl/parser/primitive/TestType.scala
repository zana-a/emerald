package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestType {
  @Test
  def testStringType = {
    val subject =
      """
        |""
        |"""
        .stripMargin
        .trim

    testType(subject, structure.primitive.String("\"\""))
  }

  def testType(input: String, expected: structure.primitive.Type) = {
    parser.Base.parse(Primitive.`type`, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testIntegerType = {
    val subject =
      """
        |42
        |"""
        .stripMargin
        .trim

    testType(subject, structure.primitive.Integer(42))
  }

  @Test
  def testBooleanType = {
    val subject =
      """
        |true
        |"""
        .stripMargin
        .trim

    testType(subject, structure.primitive.Boolean(true))
  }

  @Test
  def testListType = {
    val subject =
      """
        |[]
        |"""
        .stripMargin
        .trim

    testType(subject, structure.primitive.List(List()))
  }
}
