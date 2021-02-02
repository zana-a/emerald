package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestInteger {

  @Test
  def testSingleDigit(): Unit = {
    val subject =
      """
        |1
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(1))
  }

  @Test
  def testMultipleDigit(): Unit = {
    val subject =
      """
        |132
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(132))
  }

  def testInteger(input: String, expected: structure.primitive.Integer): Unit = {
    parser.Base.parse(Primitive.integer, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testLowerBound(): Unit = {
    val subject =
      """
        |-2147483648
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(Int.MinValue))
  }

  @Test
  def testUpperBound(): Unit = {
    val subject =
      """
        |2147483647
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(Int.MaxValue))
  }
}
