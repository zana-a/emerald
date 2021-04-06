package io.zana.zapl.test.translator.variable

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Variable extends Base {

  @Test
  def expression(): Unit = {
    Tester(
      "val a = (1 + 2) + 3",
      Variable.translate(
        Variable.parse(
          "let a = 1 + 2 + 3"
        )
      )
    )
    Tester(
      "var a = (1 + 2) + 3",
      Variable.translate(
        Variable.parse(
          "let mut a = 1 + 2 + 3"
        )
      )
    )
  }

  @Test
  def primitive(): Unit = {
    Tester(
      "val a = true",
      Variable.translate(
        Variable.parse(
          "let a = true"
        )
      )
    )
    Tester(
      "val a = 1",
      Variable.translate(
        Variable.parse(
          "let a = 1"
        )
      )
    )
    Tester(
      "val a = \"string\"",
      Variable.translate(
        Variable.parse(
          "let a = \"string\""
        )
      )
    )

    Tester(
      "var a = true",
      Variable.translate(
        Variable.parse(
          "let mut a = true"
        )
      )
    )
    Tester(
      "var a = 1",
      Variable.translate(
        Variable.parse(
          "let mut a = 1"
        )
      )
    )
    Tester(
      "var a = \"string\"",
      Variable.translate(
        Variable.parse(
          "let mut a = \"string\""
        )
      )
    )
  }

  @Test
  def call(): Unit = {
    Tester(
      "val a = a()",
      Variable.translate(
        Variable.parse(
          "let a = a()"
        )
      )
    )
    Tester(
      "val a = A.b()",
      Variable.translate(
        Variable.parse(
          "let a = A::b()"
        )
      )
    )

    Tester(
      "var a = a()",
      Variable.translate(
        Variable.parse(
          "let mut a = a()"
        )
      )
    )
    Tester(
      "var a = A.b()",
      Variable.translate(
        Variable.parse(
          "let mut a = A::b()"
        )
      )
    )
  }

}