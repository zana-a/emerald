package io.zana.zapl.test.parser.statics

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class List extends Base {

  @Test
  def list(): Unit = {

    Tester(
      Tools.parser,
      "List<Any>",
      Tools.list(Tools.any)
    )
  }

}
