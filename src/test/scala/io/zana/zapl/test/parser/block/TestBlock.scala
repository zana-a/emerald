package io.zana.zapl.test.parser.block

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestBlock {

  import parser.block.{Block => Parser}
  import structure.block.{Block => Structure}
  import structure.call._
  import structure.comment.LineComment
  import structure.common.Identifier
  import structure.primitive
  import structure.variable.Variable
  import test.parser.Tester

  @Test
  def empty_block(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |end
        |"""
        .stripMargin,
      Structure(
        body = List()
      )
    )
  }

  @Test
  def single_item(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |  a = 1
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          Variable(
            name = Identifier("a"),
            body = primitive.Integer(1)
          )
        )
      )
    )
  }

  @Test
  def multi_item(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |  a = 1
        |  b = 2
        |  c = 3
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          Variable(
            name = Identifier("a"),
            body = primitive.Integer(1)
          ),
          Variable(
            name = Identifier("b"),
            body = primitive.Integer(2)
          ),
          Variable(
            name = Identifier("c"),
            body = primitive.Integer(3)
          )
        )
      )
    )
  }

  @Test
  def line_comment_within_block(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |# comment
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          LineComment(" comment")
        )
      )
    )

    Tester.tester(
      Parser.block,
      """
        |do
        |# comment
        |# comment
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          LineComment(" comment"),
          LineComment(" comment")
        )
      )
    )
  }

  @Test
  def variable_within_block(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |a = 2
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          Variable(
            name = Identifier("a"),
            body = primitive.Integer(2)
          )
        )
      )
    )
  }

  @Test
  def expression_within_block(): Unit = {
    ???
  }

  @Test
  def control_within_block(): Unit = {
    ???
  }

  @Test
  def call_within_block(): Unit = {
    Tester.tester(
      Parser.block,
      """
        |do
        |  f()
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          FunctionCall(
            name = Identifier("f"),
            params = List()
          )
        )
      )
    )

    Tester.tester(
      Parser.block,
      """
        |do
        |  A::f()
        |end
        |"""
        .stripMargin,
      Structure(
        body = List(
          ModuleCall(
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
  }
}
