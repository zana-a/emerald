package io.zana.zapl.test.parser.control

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestCond {

  import parser.control.{Control => Parser}
  import structure.control.{Arm, Cond => Structure}
  import test.parser.Tester

  @Test
  def empty_cond(): Unit = {
    Tester.tester(
      Parser.cond,
      """
        |cond do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        arms = List(),
        default = Arm(
          guard = ???,
          command = ???
        )
      )
    )
  }

  @Test
  def default_cond(): Unit = {
    Tester.tester(
      Parser.cond,
      """
        |cond do
        |  _ => true
        |end
        |"""
        .stripMargin,
      Structure(
        arms = List(),
        default = Arm(
          guard = ???,
          command = ???
        )
      )
    )
  }

  @Test
  def single_item_cond(): Unit = {
    Tester.tester(
      Parser.cond,
      """
        |cond do
        |  false => false
        |  _ => true
        |end
        |"""
        .stripMargin,
      Structure(
        arms = List(),
        default = Arm(
          guard = ???,
          command = ???
        )
      )
    )
  }

  @Test
  def multi_item_cond(): Unit = {
    Tester.tester(
      Parser.cond,
      """
        |cond do
        |  true => true
        |  false => false
        |  _ => false
        |end
        |"""
        .stripMargin,
      Structure(
        arms = List(),
        default = Arm(
          guard = ???,
          command = ???
        )
      )
    )
  }
}
