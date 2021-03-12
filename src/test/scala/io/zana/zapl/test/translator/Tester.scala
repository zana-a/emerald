package io.zana.zapl.test.translator

import org.junit.Assert._

object Tester {

  def apply(expected: String, result: String): Unit = {
    assertEquals(expected, result)
  }
}
