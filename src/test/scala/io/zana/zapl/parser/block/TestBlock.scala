package io.zana.zapl.parser.block

import io.zana.zapl.parser.block
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestBlock {

  def testBlock(input: String, expected: structure.block.Block): Unit = {
    parser.Base.parse(block.Block.block, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testEmptyBlock(): Unit = {
    val subject =
      """
        |do
        |end
        |"""
        .stripMargin
        .trim
    testBlock(subject, structure.block.Block(List()))
  }

  @Test
  def testVariableInBlock(): Unit = {
    val subject =
      """
        |do
        |  a = "hello!"
        |  a = 1
        |  a = true
        |  a = []
        |end
        |"""
        .stripMargin
        .trim
    testBlock(
      subject, structure.block.Block(
        List(
          structure.variable.Variable(
            structure.common.Identifier("a"),
            structure.primitive.String("\"hello!\"")),
          structure.variable.Variable(
            structure.common.Identifier("a"),
            structure.primitive.Integer(1)),
          structure.variable.Variable(
            structure.common.Identifier("a"),
            structure.primitive.Boolean(true)),
          structure.variable.Variable(
            structure.common.Identifier("a"),
            structure.primitive.List(List()))
        )
      )
    )
  }

  //TODO: Test that expression work in block
}
