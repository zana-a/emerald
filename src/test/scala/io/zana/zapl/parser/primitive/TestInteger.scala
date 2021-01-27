package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestInteger {

  def testInteger(input: String, expected: structure.primitive.Integer) = {
    parser.Base.parse(parser.Primitive.integer, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testSingleDigit = {
    val subject =
      """
        |1
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(1))
  }

  @Test
  def testMultipleDigit = {
    val subject =
      """
        |132
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(132))
  }


  @Test
  def testLowerBound = {
    val subject =
      """
        |-2147483648
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(Int.MinValue))
  }

  @Test
  def testUpperBound = {
    val subject =
      """
        |2147483647
        |"""
        .stripMargin
        .trim

    testInteger(subject, structure.primitive.Integer(Int.MaxValue))
  }
}
