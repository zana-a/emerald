package io.zana.zapl.parser.comment

import io.zana.zapl.parser.module
import io.zana.zapl.parser.program.Program
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestLineComment {

  @Test
  def testEmptyComment(): Unit = {
    val subject =
      """
        |#
        |"""
        .stripMargin
        .trim

    testLineComment(subject, List(structure.comment.LineComment("")))
  }

  def testLineComment(input: String,
                      expected: List[Any]): Unit = {
    parser.Base.parse(Program.build, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testValidCodeComment(): Unit = {
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
  def testInnerModuleComment(): Unit = {
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
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testInnerBlockComment(): Unit = {
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
  def testObnoxiouslyPlacedComment(): Unit = {
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
