package io.zana.zapl.test.translator.primitive

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class String extends Base {

  @Test
  def empty(): Unit = {
    Tester(
      "\"\"",
      translate(
        parse("\"\"")
      )
    )
  }

  @Test
  def simple(): Unit = {
    Tester(
      "\"demo\"",
      translate(
        parse("\"demo\"")
      )
    )
  }

  @Test
  def withWhitespace(): Unit = {
    Tester(
      "\"\\t\\n \"",
      translate(
        parse("\"\\t\\n \"")
      )
    )
  }

  @Test
  def complex(): Unit = {
    Tester(
      "\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\"",
      translate(
        parse("\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\"")
      )
    )
  }
}
