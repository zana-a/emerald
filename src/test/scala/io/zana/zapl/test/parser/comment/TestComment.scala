package io.zana.zapl.test.parser.comment

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestComment {

  import parser.comment.{LineComment => Parser}
  import structure.comment.{LineComment => Structure}
  import test.parser.Tester

  @Test
  def lineComment(): Unit = {
    Tester.tester(
      Parser.lineComment,
      """
        |# Some comment here
        |"""
        .stripMargin,
      Structure(" Some comment here")
    )
  }
}
