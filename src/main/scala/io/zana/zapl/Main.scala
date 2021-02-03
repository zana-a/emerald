package io.zana.zapl

object Main extends App {

  import parser.expression.Arithmetic

  Runner.fromFile(args, Arithmetic.expression)
}
