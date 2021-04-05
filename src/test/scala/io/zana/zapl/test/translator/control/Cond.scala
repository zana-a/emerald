package io.zana.zapl.test.translator.control

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Cond extends Base {

  @Test
  def single(): Unit = {
    Tester(
      """
        |if (true) true
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
    Tester(
      """
        |if (false) true
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |cond do
            |  false => true
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def double(): Unit = {
    Tester(
      """
        |if (true) true
        |else false
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |cond do
            | true => true
            |  _ => false
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def triple(): Unit = {
    Tester(
      """
        |if (true) true
        |else if (false) false
        |else print()
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |cond do
            | true => true
            | false => false
            |  _ => print()
            |end
            |""".stripMargin
        )
      )
    )
  }

  @Test
  def multiple(): Unit = {
    Tester(
      """
        |if (true || false) false
        |else if (true && true) true
        |else if (false || false) false
        |else print()
        |"""
        .stripMargin
        .trim,
      translate(
        parse(
          """
            |cond do
            | true || false => false
            | true && true => true
            | false || false => false
            |  _ => print()
            |end
            |""".stripMargin
        )
      )
    )
  }
}
