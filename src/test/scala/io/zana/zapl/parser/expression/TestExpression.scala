package io.zana.zapl.parser.expression


import io.zana.zapl.parser.{Base, Expression}
import io.zana.zapl.structure.expression.{Expression => Expressor}
import org.junit.Assert._
import org.junit.Test

class TestExpression {

  def testExpression(input: String, expected: Expressor) = {
    Base.parse(Expression.expression, input) match {
      case Base.Success(s, _) => assertEquals(expected, s)
      case Base.Failure(s, _) => assert(false, s)
      case Base.Error(s, _) => assert(false, s)
    }
  }

  //  @Test
  //  def testLogicExpression = {
  //    val subject =
  //      """
  //        |true && false
  //        |"""
  //        .stripMargin
  //        .trim
  //    testExpression(
  //      subject,
  //      Expressor(
  //        "true"
  //      )
  //    )
  //  }
}