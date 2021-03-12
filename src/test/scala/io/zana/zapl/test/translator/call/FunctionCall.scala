package io.zana.zapl.test.translator.call

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class FunctionCall extends
  Base {

  @Test
  def simple(): Unit = {
    Tester(
      "a()",
      translate(
        parse(
          "a()"
        )
      )
    )
  }

}
