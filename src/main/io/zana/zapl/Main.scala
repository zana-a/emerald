package io.zana.zapl

object Main extends App {

  import parser.Parser

  println(Parser.parseAll(Parser.Function.function,
    """
      |  def a() = do
      |
      |  end
      |""".stripMargin))
}
