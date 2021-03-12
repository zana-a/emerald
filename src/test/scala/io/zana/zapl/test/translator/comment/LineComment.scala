package io.zana.zapl.test.translator.comment

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class LineComment {


  @Test
  def simple(): Unit = {
    Tester(
      "// hello",
      io.zana.zapl.translator.comment.LineComment.apply(
        io.zana.zapl.structure.comment.LineComment(" hello")
      )
    )
  }
}
