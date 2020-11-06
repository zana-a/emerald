package io.zana.zapl

import org.junit.Assert._
import org.junit._
import io.zana.zapl.Main.hello

class MainTest {


  @Test
  def `says hello`(): Unit =  {
    assertEquals(hello("Zana"), "Hello, Zana")
  }
}
