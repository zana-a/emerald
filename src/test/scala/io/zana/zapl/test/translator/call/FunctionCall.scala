package io.zana.zapl.test.translator.call

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class FunctionCall extends Base {

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

  @Test
  def withParam(): Unit = {
    Tester(
      "a(1, List(), true, \"demo\", b(), A.b())",
      translate(
        parse(
          "a(1, [], true, \"demo\", b(), A::b())"
        )
      )
    )
    Tester(
      "a(1 + 2)",
      translate(
        parse(
          "a(1 + 2)"
        )
      )
    )
  }

}
