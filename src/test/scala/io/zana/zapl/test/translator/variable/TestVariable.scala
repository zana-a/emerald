package io.zana.zapl.test.translator.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.variable.{Variable => Structure}
import io.zana.zapl.test.translator.Tester
import io.zana.zapl.translator.variable.{Variable => Translator}
import org.junit.Test

class TestVariable {

  @Test
  def integer_binding(): Unit = {
    Tester.tester(
      """
        |var item = 1
        |"""
        .stripMargin
        .trim,
      Translator.translate(
        Structure(
          name = Identifier("item"),
          body = primitive.Integer(1)
        )
      )
    )
  }

  @Test
  def string_binding(): Unit = {
    Tester.tester(
      """
        |var item = "test"
        |"""
        .stripMargin
        .trim,
      Translator.translate(
        Structure(
          name = Identifier("item"),
          body = primitive.String("\"test\"")
        )
      )
    )
  }

  @Test
  def boolean_binding(): Unit = {
    Tester.tester(
      """
        |var item = true
        |"""
        .stripMargin
        .trim,
      Translator.translate(
        Structure(
          name = Identifier("item"),
          body = primitive.Boolean(true)
        )
      )
    )
  }

  @Test
  def list_binding(): Unit = {
    Tester.tester(
      """
        |var item = List()
        |"""
        .stripMargin
        .trim,
      Translator.translate(
        Structure(
          name = Identifier("item"),
          body = primitive.List(List())
        )
      )
    )
  }
}
