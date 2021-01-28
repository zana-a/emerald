package io.zana.zapl.parser.block

import io.zana.zapl.structure.Identifier
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestBlock {
  def testBlock(input: String, expected: structure.Block) = {
    parser.Base.parse(parser.Block.block, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testEmptyBlock = {
    val subject =
      """
        |do
        |end
        |"""
        .stripMargin
        .trim
    testBlock(subject, structure.Block(List()))
  }

  @Test
  def testVariableInBlock = {
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
    testBlock(subject, structure.Block(List(
      structure.Variable(Identifier("a"),
        structure.primitive.String("\"hello!\"")),
      structure.Variable(Identifier("a"), structure.primitive.Integer(1)),
      structure.Variable(Identifier("a"), structure.primitive.Boolean(true)),
      structure.Variable(Identifier("a"), structure.primitive.List(List()))
    )))
  }
}
