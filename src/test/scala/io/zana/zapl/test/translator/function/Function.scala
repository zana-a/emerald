package io.zana.zapl.test.translator.function

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Function extends Base {

  @Test
  def body(): Unit = {
    Tester(
      """
        |def a(): Any = 1 + 2
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = 1 + 2
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): Any = {
        |
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = do
            |end
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): Any = a()
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = a()
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): Any = A.b()
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = A::b()
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): Any = id
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = id
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): Any = if (true) true
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = cond do
            |  _ => true
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def static(): Unit = {
    Tester(
      """
        |def a(): scala.Boolean = true
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Boolean = true
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): scala.Int = 1
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Int = 1
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): List[scala.String] = List("hi")
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): List<String> = ["hi"]
            |""".stripMargin
        )
      )
    )
    Tester(
      """
        |def a(): scala.String = "hi"
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): String = "hi"
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def params(): Unit = {
    Tester(
      """
        |def a(a: scala.Int, b: scala.Int): scala.Int = a + b
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(a: Int, b: Int): Int = a + b
            |""".stripMargin
        )
      )
    )
  }
}