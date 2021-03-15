package io.zana.zapl

object Main extends App {
  //  Compiler(args, display = true)
  println(parser.base.Base.parse(
    parser.expression.Expression.expr,
    "fib(1) - 1"
  ))

  1 + Int.box(1)
}

