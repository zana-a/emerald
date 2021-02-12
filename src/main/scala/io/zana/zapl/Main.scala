package io.zana.zapl


object Main extends App {

  val parse = parser.base.Base.parse(
    parser.program.Program.apply,
    {
      """
        |
        |
        |"""
        .stripMargin
    }
  )

  parse match {
    case parser.base.Base.Success(s, _) => println(s)
    case parser.base.Base.Failure(_, _) => println(parse)
    case parser.base.Base.Error(_, _) => println(parse)
  }
}
