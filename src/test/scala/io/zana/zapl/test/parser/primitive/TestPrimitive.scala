package io.zana.zapl.test.parser.primitive

import io.zana.zapl.{parser, structure, test}
import org.junit.Test

class TestPrimitive {

  import parser.primitive.{Primitive => Parser}
  import structure.primitive
  import test.parser.Tester

  @Test
  def integer(): Unit = {
    Tester.tester(
      Parser.integer,
      """
        |1
        |"""
        .stripMargin,
      primitive.Integer(1)
    )
  }

  @Test
  def string(): Unit = {
    Tester.tester(
      Parser.string,
      """
        |"test"
        |"""
        .stripMargin,
      primitive.String("\"test\"")
    )
  }

  @Test
  def boolean(): Unit = {
    Tester.tester(
      Parser.boolean,
      """
        |true
        |"""
        .stripMargin,
      primitive.Boolean(true)
    )

    Tester.tester(
      Parser.boolean,
      """
        |false
        |"""
        .stripMargin,
      primitive.Boolean(false)
    )
  }

  @Test
  def list(): Unit = {
    Tester.tester(
      Parser.list,
      """
        |[1, 2, 3]
        |"""
        .stripMargin,
      primitive.List(
        List(
          primitive.Integer(1),
          primitive.Integer(2),
          primitive.Integer(3)
        )
      )
    )

    Tester.tester(
      Parser.list,
      """
        |["one", "two", "three"]
        |"""
        .stripMargin,
      primitive.List(
        List(
          primitive.String("\"one\""),
          primitive.String("\"two\""),
          primitive.String("\"three\"")
        )
      )
    )

    Tester.tester(
      Parser.list,
      """
        |[true, false, true]
        |"""
        .stripMargin,
      primitive.List(
        List(
          primitive.Boolean(true),
          primitive.Boolean(false),
          primitive.Boolean(true)
        )
      )
    )

    Tester.tester(
      Parser.list,
      """
        |[[], [], []]
        |"""
        .stripMargin,
      primitive.List(
        List(
          primitive.List(
            List()
          ),
          primitive.List(
            List()
          ),
          primitive.List(
            List()
          )
        )
      )
    )
  }
}
