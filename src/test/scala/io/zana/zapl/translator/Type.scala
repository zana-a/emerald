package io.zana.zapl.translator

import io.zana.zapl.structure
import org.junit.Assert._
import org.junit.Test

class Type {

  @Test
  def `test integer`(): Unit = {
    val source = structure.Type.Integer(1)
    val result = Translator.`type`(source)
    assertEquals(source.value.toString, result)
  }

  @Test
  def `test string`(): Unit = {
    val source = structure.Type.String("hello, world!")
    val result = Translator.`type`(source)
    assertEquals(source.value, result)
  }

  @Test
  def `test boolean`(): Unit = {
    {
      val source = structure.Type.Boolean(true)
      val result = Translator.`type`(source)
      assertEquals(source.value.toString, result)
    }

    {
      val source = structure.Type.Boolean(false)
      val result = Translator.`type`(source)
      assertEquals(source.value.toString, result)
    }
  }

  @Test
  def `test list`(): Unit = {
    {
      // Empty list
      val source = structure.Type.List(List())
      val result = Translator.`type`(source)
      assertEquals("List()", result)
    }
    {
      // List with primitive types
      val source =
        structure.Type.List(
          List(
            structure.Type.Integer(1),
            structure.Type.String("hello, world!"),
            structure.Type.Boolean(true)
          )
        )

      val result = Translator.`type`(source)
      assertEquals("List(1, \"hello, world!\", true)", result)
    }
    {
      // Deep Lists
      val source =
        structure.Type.List(
          List(
            structure.Type.List(
              List(
                structure.Type.List(List()),
                structure.Type.List(List()),
                structure.Type.List(List()),
              )
            ),
            structure.Type.List(
              List(
                structure.Type.List(List()),
                structure.Type.List(List()),
                structure.Type.List(List()),
              )
            ),
            structure.Type.List(
              List(
                structure.Type.List(List()),
                structure.Type.List(List()),
                structure.Type.List(List()),
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
