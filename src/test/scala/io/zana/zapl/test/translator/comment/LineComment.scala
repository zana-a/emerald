package io.zana.zapl.test.translator.comment

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class LineComment extends Base {


  @Test
  def simple(): Unit = {
    Tester(
      "// hello",
      Tools.LineComment.translate(
        Tools.LineComment.parse(
          "# hello"
        )
      )
    )
  }
}
