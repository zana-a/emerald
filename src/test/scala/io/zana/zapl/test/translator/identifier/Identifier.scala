package io.zana.zapl.test.translator.identifier

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Identifier {

  @Test
  def simple(): Unit = {
    Tester(
      "demo",
      io.zana.zapl.translator.identifier.Identifier(
        io.zana.zapl.structure.identifier.Identifier("demo")
      )
    )
  }
}
