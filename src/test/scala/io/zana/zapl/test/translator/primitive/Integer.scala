package io.zana.zapl.test.translator.primitive

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Integer extends Base {

  @Test
  def single(): Unit = {
    Tester(
      "1",
      translate(
        parse("1")
      )
    )
  }

  @Test
  def min(): Unit = {
    Tester(
      Integer.MIN_VALUE.toString,
      translate(
        parse(Integer.MIN_VALUE.toString)
      )
    )
  }

  @Test
  def max(): Unit = {
    Tester(
      Integer.MAX_VALUE.toString,
      translate(
        parse(Integer.MAX_VALUE.toString)
      )
    )
  }

}
