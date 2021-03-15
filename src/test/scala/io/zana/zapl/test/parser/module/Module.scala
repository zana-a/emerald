package io.zana.zapl.test.parser.module

import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Module extends Base {

  @Test
  def simple(): Unit = {
    Tester(
      Tools.parser,
      """
        |mod A do
        |end
        |""".stripMargin,
      Tools.structure(
        Identifier("A"),
        List()
      )
    )
  }


  // TODO:
  // 1. Mod with function
}
