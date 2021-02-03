package io.zana.zapl.test.parser.program

import io.zana.zapl.structure.variable.Variable
import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestProgram {

  import parser.program.{Program => Parser}
  import structure.block.Block
  import structure.call.{FunctionCall, ModuleCall}
  import structure.comment.LineComment
  import structure.common.Identifier
  import structure.function.Function
  import structure.module.Module
  import structure.primitive
  import structure.program.{Program => Structure}
  import test.parser.Tester

  @Test
  def empty_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |
        |"""
        .stripMargin,
      Structure(
        statements = List()
      )
    )
  }

  @Test
  def comment_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |# comment
        |"""
        .stripMargin,
      Structure(
        statements = List(
          LineComment(" comment")
        )
      )
    )
  }

  @Test
  def module_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |mod A do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Module(
            name = Identifier("A"),
            body = List()
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |mod A do
        |
        |end
        |
        |mod B do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Module(
            name = Identifier("A"),
            body = List()
          ),
          Module(
            name = Identifier("B"),
            body = List()
          )
        )
      )
    )
  }

  @Test
  def function_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |def f() = 1
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = primitive.Integer(1)
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |def f() = 1 + 2
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = ???
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |def f() = something
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = Identifier("something")
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |def f() = do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = Block(List())
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |def f() = g()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = FunctionCall(
              name = Identifier("g"),
              params = List()
            )
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |def f() = A::g()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Function(
            name = Identifier("f"),
            params = List(),
            body = ModuleCall(
              modules = List(
                Identifier("A")
              ),
              caller = FunctionCall(
                name = Identifier("g"),
                params = List()
              )
            )
          )
        )
      )
    )
  }

  @Test
  def call_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |f()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          FunctionCall(
            name = Identifier("f"),
            params = List()
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |A::f()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          ModuleCall(
            modules = List(
              Identifier("a")
            ),
            caller = FunctionCall(
              name = Identifier("f"),
              params = List()
            )
          )
        )
      )
    )
  }

  @Test
  def variable_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |item = 1
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
            name = Identifier("item"),
            body = primitive.Integer(1)
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |item = true
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
            name = Identifier("item"),
            body = primitive.Boolean(true)
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |item = "test"
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
            name = Identifier("item"),
            body = primitive.String("\"test\"")
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |item = []
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
            name = Identifier("item"),
            body = primitive.List(List())
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |item = f()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
            name = Identifier("item"),
            body = FunctionCall(
              name = Identifier("f"),
              params = List()
            )
          )
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |item = A::f()
        |"""
        .stripMargin,
      Structure(
        statements = List(
          Variable(
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
      )
    )
  }

  @Test
  def control_in_program(): Unit = {
    Tester.tester(
      Parser.program,
      """
        |cond do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        statements = List(
          ???
        )
      )
    )

    Tester.tester(
      Parser.program,
      """
        |loop do
        |
        |end
        |"""
        .stripMargin,
      Structure(
        statements = List(
          ???
        )
      )
    )
  }
}
