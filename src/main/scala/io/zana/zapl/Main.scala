package io.zana.zapl

import io.zana.zapl.standard.{Compiler, Error}

object Main extends App {
  //  Compiler(args, display = true)
  val ast = parser.base.Base.parse(parser.expression.Expression.apply,
    """
      |(1 + 2) + 2 * 2 - 1
      |""".stripMargin) match {
    case parser.base.Base.Success(s, _) => s
    case parser.base.Base.Failure(s, _) => Error(Error.UnknownSyntax(s))
    case parser.base.Base.Error(s, _) => Error(Error.UnknownSyntax(s))
  }

  println(io.zana.zapl.translator.expression.Expression(
    ast
  ))
}

