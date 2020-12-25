package io.zana.zapl

object Main extends App {

  import parser.Parser

  println(Parser.parseAll(Parser.Program.build,
    """
      |module A do
      |
      |end
      |""".stripMargin))
}
