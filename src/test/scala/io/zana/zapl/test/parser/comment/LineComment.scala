package io.zana.zapl.test.parser.comment

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.module.Module
import io.zana.zapl.structure.program.Program
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

  @Test
  def inProgram(): Unit = {
    Tester(
      io.zana.zapl.parser.program.Program.apply,
      """
        |# comment
        |""".stripMargin,
      Program(
        List(
          Tools.LineComment.structure(" comment")
        )
      )
    )
  }

  @Test
  def inModule(): Unit = {
    Tester(
      io.zana.zapl.parser.module.Module.apply,
      """
        |mod A do
        |# comment
        |end
        |""".stripMargin,
      Module(
        Identifier("A"),
        List(
          Tools.LineComment.structure(" comment")
        )
      )
    )
  }

  @Test
  def inBlock(): Unit = {
    Tester(
      io.zana.zapl.parser.block.Block.apply,
      """
        |do
        |# comment
        |end
        |""".stripMargin,
      Block(
        List(
          Tools.LineComment.structure(" comment")
        )
      )
    )
  }
}
