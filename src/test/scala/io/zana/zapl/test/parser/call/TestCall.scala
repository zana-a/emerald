package io.zana.zapl.test.parser.call

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestCall {

  import parser.call.{FunctionCall => FParser, ModuleCall => MParser}
  import structure.call.{FunctionCall => FStructure, ModuleCall => MStructure}
  import structure.common.Identifier
  import structure.primitive
  import test.parser.Tester

  @Test
  def function(): Unit = {
    Tester.tester(
      FParser.call,
      """
        |f()
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List()
      )
    )
  }

  @Test
  def module(): Unit = {
    Tester.tester(
      MParser.call,
      """
        |A::f()
        |"""
        .stripMargin,
      MStructure(
        modules = List(
          Identifier("A")
        ),
        caller = FStructure(
          name = Identifier("f"),
          params = List()
        )
      )
    )

    Tester.tester(
      MParser.call,
      """
        |A::B::f()
        |"""
        .stripMargin,
      MStructure(
        modules = List(
          Identifier("A"),
          Identifier("B")
        ),
        caller = FStructure(
          name = Identifier("f"),
          params = List()
        )
      )
    )
  }

  @Test
  def params(): Unit = {
    Tester.tester(
      FParser.call,
      """
        |f(1)
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List(
          primitive.Integer(1)
        )
      )
    )

    Tester.tester(
      FParser.call,
      """
        |f(true)
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List(
          primitive.Boolean(true)
        )
      )
    )

    Tester.tester(
      FParser.call,
      """
        |f("string")
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List(
          primitive.String("\"string\"")
        )
      )
    )

    Tester.tester(
      FParser.call,
      """
        |f([])
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List(
          primitive.List(List())
        )
      )
    )

    Tester.tester(
      FParser.call,
      """
        |f(a, f(), A::B::f())
        |"""
        .stripMargin,
      FStructure(
        name = Identifier("f"),
        params = List(
          Identifier("a"),
          FStructure(
            name = Identifier("f"),
            params = List()
          ),
          MStructure(
            modules = List(
              Identifier("A"),
              Identifier("B"),
            ),
            caller = FStructure(
              name = Identifier("f"),
              params = List()
            )
          )
        )
      )
    )
  }
}
