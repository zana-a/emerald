package io.zana.zapl.test.translator.primitive

import io.zana.zapl.structure.primitive.{Boolean => Structure}
import io.zana.zapl.test.translator.Tester
import io.zana.zapl.translator.primitive.Boolean

class TestBoolean {

  def `true`(): Unit = {
    Tester.tester(
      """
        |true
        |"""
        .stripMargin
        .trim,
      Boolean.translate(
        Structure(true)
      )
    )
  }

  def `false`(): Unit = {
    Tester.tester(
      """
        |false
        |"""
        .stripMargin
        .trim,
      Boolean.translate(
        Structure(false)
      )
    )
  }
}
