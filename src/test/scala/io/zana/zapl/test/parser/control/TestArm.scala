package io.zana.zapl.test.parser.control

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestArm {

  import parser.control.{Control => Parser}
  import structure.control.{Arm => Structure}
  import test.parser.Tester

  @Test
  def arm_guard(): Unit = {
    Tester.tester(
      Parser.guard,
      """
        |true && false
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }

  @Test
  def arm_command_block(): Unit = {
    Tester.tester(
      Parser.command,
      """
        |true && false => do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }

  @Test
  def arm_command_identifier(): Unit = {
    Tester.tester(
      Parser.command,
      """
        |true && false => something
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }

  @Test
  def arm_command_expression(): Unit = {
    Tester.tester(
      Parser.command,
      """
        |true && false => 1 + 2
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }

  @Test
  def arm_command_call(): Unit = {
    Tester.tester(
      Parser.command,
      """
        |true && false => f()
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )

    Tester.tester(
      Parser.command,
      """
        |true && false => A::f()
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }

  @Test
  def arm_command_type(): Unit = {
    Tester.tester(
      Parser.command,
      """
        |true && false => 1
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )

    Tester.tester(
      Parser.command,
      """
        |true && false => true
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )

    Tester.tester(
      Parser.command,
      """
        |true && false => []
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )

    Tester.tester(
      Parser.command,
      """
        |true && false => "test"
        |"""
        .stripMargin,
      Structure(
        guard = ???,
        command = ???
      )
    )
  }
}
