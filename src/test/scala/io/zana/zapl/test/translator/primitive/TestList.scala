package io.zana.zapl.test.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.test.translator.Tester
import io.zana.zapl.translator.primitive.List
import org.junit.Test

class TestList {

  @Test
  def empty_list(): Unit = {
    Tester.tester(
      """
        |List.scala()
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(scala.List())
      )
    )
  }

  @Test
  def string_list(): Unit = {
    Tester.tester(
      """
        |List.scala("one", "two", "three")
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(
          scala.List(
            primitive.String("\"one\""),
            primitive.String("\"two\""),
            primitive.String("\"three\"")
          ),
        )
      )
    )
  }

  @Test
  def integer_list(): Unit = {
    Tester.tester(
      """
        |List.scala(1, 2, 3)
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(
          scala.List(
            primitive.Integer(1),
            primitive.Integer(2),
            primitive.Integer(3)
          ),
        )
      )
    )
  }

  @Test
  def boolean_list(): Unit = {
    Tester.tester(
      """
        |List.scala(true, false, true)
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(
          scala.List(
            primitive.Boolean(true),
            primitive.Boolean(false),
            primitive.Boolean(true)
          ),
        )
      )
    )
  }

  @Test
  def list_list(): Unit = {
    Tester.tester(
      """
        |List.scala(List.scala(), List.scala(), List.scala())
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(
          scala.List(
            primitive.List(scala.List()),
            primitive.List(scala.List()),
            primitive.List(scala.List())
          ),
        )
      )
    )

    Tester.tester(
      """
        |List.scala(List.scala(List.scala(List.scala(List.scala()))))
        |"""
        .stripMargin
        .trim,
      List.translate(
        primitive.List(
          scala.List(
            primitive.List(
              scala.List(
                primitive.List(
                  scala.List(
                    primitive.List(
                      scala.List(
                        primitive.List(
                          scala.List()
                        ),
                      )
                    ),
                  )
                ),
              )
            ),
          ),
        )
      )
    )
  }
}
