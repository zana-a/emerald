package io.zana.zapl.test.translator.primitive

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Boolean extends Base {

  @Test
  def `true`(): Unit = {
    Tester(
      "true",
      translate(
        parse(
          "true"
        )
      )
    )
  }

  @Test
  def `false`(): Unit = {
    Tester(
      "false",
      translate(
        parse(
          "false"
        )
      )
    )
  }
}
