package io.zana.zapl

object Main extends App {
//  Builder.write(args)


    val source =
      """
        |def a(a: List<Int>): List<Int> = true
        |""".stripMargin

    parser.Base.parseAll(parser.program.Program.program, source) match {
      case parser.Base.Success(s, _) =>
        println(
          translator.program.Program.translate(s)
        )
      case parser.Base.Failure(s, _) => System.err.println(s)
      case parser.Base.Error(s, _) => System.err.println(s)
    }
}
