package io.zana.zapl.test.parser.statics

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Integer extends Base {

  @Test
  def integer(): Unit = {

    Tester(
      Tools.parser,
      "Int",
      Tools.integer
    )
  }

}
