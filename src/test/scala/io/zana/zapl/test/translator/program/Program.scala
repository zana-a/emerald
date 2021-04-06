package io.zana.zapl.test.translator.program

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Program extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      """
        |object Application extends App {
        |
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          ""
        )
      )
    )
  }

  @Test
  def lineComment(): Unit = {
    Tester(
      """
        |object Application extends App {
        |// comment
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |# comment
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def module(): Unit = {
    Tester(
      """
        |object Application extends App {
        |object A {
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
        |object Application extends App {
        |a()
        |A.b()
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |a()
            |A::b()
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def function(): Unit = {
    Tester(
      """
        |object Application extends App {
        |def a(): Any = 1
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |def a(): Any = 1
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def variable(): Unit = {
    Tester(
      """
        |object Application extends App {
        |val a = 2
        |var b = 3
        |b = 4
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |let a = 2
            |let mut b = 3
            |b = 4
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def control(): Unit = {
    Tester(
      """
        |object Application extends App {
        |if (true) true
        |}
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |cond do
            |  _ => true
            |end
            |""".stripMargin
        )
      )
    )
  }
}