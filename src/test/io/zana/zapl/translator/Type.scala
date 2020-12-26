package io.zana.zapl.translator

import io.zana.zapl.structure.Type
import org.junit.Assert._
import org.junit.Test

class Type {

  @Test
  def `test integer`(): Unit = {
    val source = Type.Integer(1)
    val result = Translator.`type`(source)
    assertEquals(source.value.toString, result)
  }

  @Test
  def `test string`(): Unit = {
    val source = Type.String("hello, world!")
    val result = Translator.`type`(source)
    assertEquals(source.value, result)
  }

  @Test
  def `test boolean`(): Unit = {
    {
      val source = Type.Boolean(true)
      val result = Translator.`type`(source)
      assertEquals(source.value.toString, result)
    }

    {
      val source = Type.Boolean(false)
      val result = Translator.`type`(source)
      assertEquals(source.value.toString, result)
    }
  }

  @Test
  def `test list`(): Unit = {
    {
      // Empty list
      val source = Type.List(List())
      val result = Translator.`type`(source)
      assertEquals("List()", result)
    }
    {
      // List with primitive types
      val source =
        Type.List(
          List(
            Type.Integer(1),
            Type.String("hello, world!"),
            Type.Boolean(true)
          )
        )

      val result = Translator.`type`(source)
      assertEquals("List(1, \"hello, world!\", true)", result)
    }
    {
      // Deep Lists
      val source =
        Type.List(
          List(
            Type.List(
              List(
                Type.List(List()),
                Type.List(List()),
                Type.List(List()),
              )
            ),
            Type.List(
              List(
                Type.List(List()),
                Type.List(List()),
                Type.List(List()),
              )
            ),
            Type.List(
              List(
                Type.List(List()),
                Type.List(List()),
                Type.List(List()),
              )
            ),
          )
        )

      val result = Translator.`type`(source)
      assertEquals(
        "List(List(List(), List(), List()), List(List(), List(), List()), List(List(), List(), List()))",
        result
      )
    }
  }
}
