package io.zana.zapl.parser.comment

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestLineComment {

  @Test
  def emptyComment = {
    val subject =
      """
        |#
        |"""
        .stripMargin
        .trim
    parser.Base.parse(parser.Comment.singleLine, subject) match {
      case parser.Base.Success(s, _) =>
        assertEquals(
          structure.comment.LineComment(""),
          s
        )

      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def validCodeComment = {
    val subject =
      """
        |#mod A do end
        |"""
        .stripMargin
        .trim
    parser.Base.parse(parser.Comment.singleLine, subject) match {
      case parser.Base.Success(s, _) =>
        assertEquals(
          structure.comment.LineComment("mod A do end"),
          s
        )
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }
}
