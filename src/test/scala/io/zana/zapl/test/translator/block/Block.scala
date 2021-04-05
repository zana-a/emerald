package io.zana.zapl.test.translator.block

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Block extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      """
        |{
        |
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def lineComment(): Unit = {
    Tester(
      """
        |{
        |// comment
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |# comment
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def variable(): Unit = {
    Tester(
      """
        |{
        |val a = 2
        |var b = 2
        |a = 3
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |let a = 2
            |let mut b = 2
            |a = 3
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def call(): Unit = {
    Tester(
      """
        |{
        |a()
        |A.b()
        |A.B.C.d()
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |a()
            |A::b()
            |A::B::C::d()
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def expression(): Unit = {
    Tester(
      """
        |{
        |1 + 2
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |1 + 2
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def control(): Unit = {
    Tester(
      """
        |{
        |if (true) true
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |do
            |cond do
            | true => true
            |end
            |end
            |""".stripMargin
        )
      )
    )
  }
}