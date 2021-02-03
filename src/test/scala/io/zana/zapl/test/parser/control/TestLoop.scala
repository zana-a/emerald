package io.zana.zapl.test.parser.control

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestLoop {

  import parser.control.{Control => Parser}
  import structure.control.{Arm, Loop => Structure}
  import test.parser.Tester

  @Test
  def empty_loop(): Unit = {
    Tester.tester(
      Parser.loop,
      """
        |loop do
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
  def default_loop(): Unit = {
    Tester.tester(
      Parser.loop,
      """
        |loop do
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
  def single_item_loop(): Unit = {
    Tester.tester(
      Parser.loop,
      """
        |loop do
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
  def multi_item_loop(): Unit = {
    Tester.tester(
      Parser.loop,
      """
        |loop do
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
