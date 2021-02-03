package io.zana.zapl.test.parser.variable

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestVariable {

  import parser.variable.{Variable => Parser}
  import structure.call.{FunctionCall, ModuleCall}
  import structure.common.Identifier
  import structure.primitive
  import structure.variable.{Variable => Structure}
  import test.parser.Tester

  @Test
  def type_variable(): Unit = {
    Tester.tester(
      Parser.variable,
      """
        |item = 1
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = primitive.Integer(1)
      )
    )

    Tester.tester(
      Parser.variable,
      """
        |item = "test"
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = primitive.String("\"test\"")
      )
    )

    Tester.tester(
      Parser.variable,
      """
        |item = true
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = primitive.Boolean(true)
      )
    )

    Tester.tester(
      Parser.variable,
      """
        |item = []
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = primitive.List(List())
      )
    )
  }

  @Test
  def expression_variable(): Unit = {
    Tester.tester(
      Parser.variable,
      """
        |item = 1 + 2
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = ???
      )
    )
  }

  @Test
  def call_variable(): Unit = {
    Tester.tester(
      Parser.variable,
      """
        |item = f()
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = FunctionCall(
          name = Identifier("f"),
          params = List()
        )
      )
    )

    Tester.tester(
      Parser.variable,
      """
        |item = A::f()
        |"""
        .stripMargin,
      Structure(
        name = Identifier("item"),
        body = ModuleCall(
          modules = List(
            Identifier("A")
          ),
          caller = FunctionCall(
            name = Identifier("f"),
            params = List()
          )
        )
      )
    )
  }
}
