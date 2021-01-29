package io.zana.zapl.parser.comment

import io.zana.zapl.parser.module
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestLineComment {

  @Test
  def testEmptyComment = {
    val subject =
      """
        |#
        |"""
        .stripMargin
        .trim

    testLineComment(subject, List(structure.comment.LineComment("")))
  }

  def testLineComment(input: String,
                      expected: List[Any]) = {
    parser.Base.parse(parser.Program.build, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testValidCodeComment = {
    val subject =
      """
        |#mod A do end
        |"""
        .stripMargin
        .trim

    testLineComment(
      subject,
      List(structure.comment.LineComment("mod A do end"))
    )
  }

  @Test
  def testInnerModuleComment = {
    val subject =
      """
        |mod A do
        |# comment
        |end
        |"""
        .stripMargin
        .trim

    val expected = structure.module.Module(
      structure.common.Identifier("A"),
      List(
        structure.comment.LineComment(" comment")
      )
    )

    parser.Base.parse(module.Module.module, subject) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testInnerBlockComment = {
    val subject =
      """
        |def f() = do
        |# comment
        |end
        |"""
        .stripMargin
        .trim

    testLineComment(
      subject,
      List(
        structure.function.Function(
          structure.common.Identifier("f"),
          List(),
          structure.block.Block(
            List(
              structure.comment.LineComment(" comment")
            )
          )
        )
      )
    )
  }

  @Test
  def testAbnoxiouslyPlacedComment = {
    val subject =
      """
        |def a() = 1 # comment
        |"""
        .stripMargin
        .trim

    testLineComment(
      subject,
      List(
        structure.function.Function(
          structure.common.Identifier("a"),
          List(),
          structure.primitive.Integer(1)
        ),
        structure.comment.LineComment(" comment")
      )
    )
  }
}
