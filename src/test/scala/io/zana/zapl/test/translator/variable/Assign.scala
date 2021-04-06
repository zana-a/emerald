package io.zana.zapl.test.translator.variable

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Assign extends Base {

  @Test
  def expression(): Unit = {
    Tester(
      "a = (1 + 2) + 3",
      Assign.translate(
        Assign.parse(
          "a = 1 + 2 + 3"
        )
      )
    )
  }

  @Test
  def primitive(): Unit = {
    Tester(
      "a = true",
      Assign.translate(
        Assign.parse(
          "a = true"
        )
      )
    )
    Tester(
      "a = 1",
      Assign.translate(
        Assign.parse(
          "a = 1"
        )
      )
    )
    Tester(
      "a = \"string\"",
      Assign.translate(
        Assign.parse(
          "a = \"string\""
        )
      )
    )
  }

  @Test
  def call(): Unit = {
    Tester(
      "a = a()",
      Assign.translate(
        Assign.parse(
          "a = a()"
        )
      )
    )
    Tester(
      "a = A.b()",
      Assign.translate(
        Assign.parse(
          "a = A::b()"
        )
      )
    )
  }
}
