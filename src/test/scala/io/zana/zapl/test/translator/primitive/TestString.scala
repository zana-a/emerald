package io.zana.zapl.test.translator.primitive

import io.zana.zapl.{structure, test, translator}
import org.junit.Test

class TestString {

  import structure.primitive
  import test.translator.Tester
  import translator.primitive.String

  @Test
  def empty_string(): Unit = {
    Tester.tester(
      expected =
        """
          |""
          |"""
          .stripMargin
          .trim,
      result = String.translate(
        primitive.String("\"\"")
      )
    )
  }

  @Test
  def spacey_string(): Unit = {
    Tester.tester(
      expected =
        """
          |"one two three"
          |"""
          .stripMargin
          .trim,
      result = String.translate(
        primitive.String("\"one two three\"")
      )
    )
  }

  @Test
  def special_chars_string(): Unit = {
    Tester.tester(
      expected =
        """
          |"\"hello\""
          |"""
          .stripMargin
          .trim,
      result = String.translate(
        primitive.String("\"\\\"hello\\\"\"")
      )
    )
  }
}
