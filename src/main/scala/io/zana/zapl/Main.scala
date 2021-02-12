package io.zana.zapl

import io.zana.zapl.parser.base.Base

object Main extends App {
  //  Builder.write(args)
  val source =
    """
      |def a(a: List<Int>): List<Int> = true
      |""".stripMargin

  Base.parseAll(parser.program.Program.program, source) match {
    case Base.Success(s, _) =>
      println(
        translator.program.Program.translate(s)
      )
    case Base.Failure(s, _) => System.err.println(s)
    case Base.Error(s, _) => System.err.println(s)
  }
}
