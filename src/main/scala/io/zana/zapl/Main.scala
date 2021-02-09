package io.zana.zapl

object Main extends App {
  println(Builder.fromFile(args))

  //  val result = Builder.Parser.parse(
  //    """
  //      |def a(x: String, b: Int) -> Boolean = true
  //      |""".stripMargin,
  //    parser.function.Function.function
  //  )

  //  println(result)
}
