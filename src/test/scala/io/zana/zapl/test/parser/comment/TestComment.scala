package io.zana.zapl.test.parser.comment

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestComment {

  import parser.comment.{Comment => Parser}
  import structure.comment.{LineComment => Structure}
  import test.parser.Tester

  @Test
  def lineComment(): Unit = {
    Tester.tester(
      Parser.lineComment,
      """
        |# Line comment
        |"""
        .stripMargin,
      Structure(" Line Comment")
    )
  }
}
