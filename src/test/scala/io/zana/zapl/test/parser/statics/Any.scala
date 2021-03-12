package io.zana.zapl.test.parser.statics

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Any extends Base {

  @Test
  def any(): Unit = {

    Tester(
      Tools.parser,
      "Any",
      Tools.any
    )
  }

}
