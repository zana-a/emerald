package io.zana.zapl.test.translator.module

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Module extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      """
        |object A {
        |
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def singleFunction(): Unit = {
    Tester(
      """
        |object A {
        |def a(): Predef.Int = 1
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |  def a(): Int = 1
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def multiFunction(): Unit = {
    Tester(
      """
        |object A {
        |def a(): Predef.Int = 1
        |def b(): Predef.Int = 2
        |def c(): Predef.Int = 3
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |  def a(): Int = 1
            |  def b(): Int = 2
            |  def c(): Int = 3
            |end
            |""".stripMargin
        )
      )
    )
  }
}
