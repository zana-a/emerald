package io.zana.zapl.test.parser.statics

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class String extends Base {

  @Test
  def string(): Unit = {

    Tester(
      Tools.parser,
      "String",
      Tools.string
    )
  }

}
