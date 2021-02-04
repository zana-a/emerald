package io.zana.zapl.test.translator.primitive

import io.zana.zapl.{structure, test, translator}
import org.junit.Test

class TestInteger {

  import structure.primitive
  import test.translator.Tester
  import translator.primitive.{Integer => Translator}

  @Test
  def single_digit_integer(): Unit = {
    Tester.tester(
      expected =
        """
          |1
          |"""
          .stripMargin
          .trim,
      result = Translator.translate(
        primitive.Integer(1)
      )
    )
  }

  @Test
  def multi_digit_integer(): Unit = {
    Tester.tester(
      expected =
        """
          |12345
          |"""
          .stripMargin
          .trim,
      result = Translator.translate(
        primitive.Integer(12345)
      )
    )
  }

  @Test
  def zero_prefixed_integer(): Unit = {
    Tester.tester(
      expected =
        """
          |2345
          |"""
          .stripMargin
          .trim,
      result = Translator.translate(
        primitive.Integer(02345)
      )
    )
  }
}
