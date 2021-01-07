package io.zana.zapl

import io.zana.zapl.parser.Parser

object Main {

  val source = {
    """
      |def a() = do
      |
      |end
      |""".stripMargin
  }

  def main(args: Array[String]): Unit = {
    println(Parser.parseAll(Parser.Program.build, source))
  }
}
