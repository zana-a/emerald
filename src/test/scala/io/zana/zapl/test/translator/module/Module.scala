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
        |def a(): scala.Int = 1
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
  def singleModule(): Unit = {
    Tester(
      """
        |object A {
        |object B {
        |
        |}
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |  mod B do
            |
            |  end
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def multiModule(): Unit = {
    Tester(
      """
        |object A {
        |object B {
        |
        |}
        |object C {
        |
        |}
        |object D {
        |
        |}
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |  mod B do
            |
            |  end
            |
            |  mod C do
            |
            |  end
            |
            |  mod D do
            |
            |  end
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def nestedModule(): Unit = {
    Tester(
      """
        |object A {
        |object B {
        |object C {
        |object D {
        |
        |}
        |}
        |}
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |mod A do
            |  mod B do
            |    mod C do
            |      mod D do
            |
            |      end
            |    end
            |  end
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
        |def a(): scala.Int = 1
        |def b(): scala.Int = 2
        |def c(): scala.Int = 3
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
