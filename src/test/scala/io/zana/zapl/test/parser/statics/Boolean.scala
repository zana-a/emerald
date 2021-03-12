package io.zana.zapl.test.parser.statics

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Boolean extends Base {

  @Test
  def boolean(): Unit = {

    Tester(
      Tools.parser,
      "Boolean",
      Tools.boolean
    )
  }

}
