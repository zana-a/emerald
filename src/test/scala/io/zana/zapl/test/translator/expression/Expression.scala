package io.zana.zapl.test.translator.expression

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Expression extends Base {

  @Test
  def digit(): Unit = {
    Tester(
      "1",
      translate(
        parse(
          "1"
        )
      )
    )
  }

  @Test
  def simple(): Unit = {
    Tester(
      "(1 + 2)",
      translate(
        parse(
          "1 + 2"
        )
      )
    )
  }

  @Test
  def brackets(): Unit = {
    Tester(
      "((1 + 2) / 2)",
      translate(
        parse(
          "(1 + 2) / 2"
        )
      )
    )
  }

  @Test
  def logic(): Unit = {
    Tester(
      "(true && false)",
      translate(
        parse(
          "true && false"
        )
      )
    )
  }
}
