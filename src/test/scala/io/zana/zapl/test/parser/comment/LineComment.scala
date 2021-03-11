package io.zana.zapl.test.parser.comment

import io.zana.zapl.test.parser.Tester
import org.junit.Test

class LineComment extends Base {

  @Test
  def plain(): Unit = {
    Tester(
      Tools.LineComment.parser,
      "# test",
      Tools.LineComment.structure(" test")
    )
  }

  @Test
  def complex(): Unit = {
    Tester(
      Tools.LineComment.parser,
      "# !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
      Tools.LineComment.structure(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~")
    )
  }
}
