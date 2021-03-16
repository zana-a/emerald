package io.zana.zapl

object Main extends App {
  //  Compiler(args, display = true)
  println(parser.base.Base.parse(
    parser.function.Function.apply,
    """
      |def add(a: Int, b: Int): Int = a + b
      |""".stripMargin
  ))
}

