package io.zana.zapl

import io.zana.zapl.parser.{Base, primitive}

object Main extends App {

  val result = Base.parse(
    primitive.Primitive.list,
    """
      |["zana", true]
      |"""
      .stripMargin
      .trim
  )

  result match {
    case parser.Base.Success(s, _) =>
      println(translator.primitive.List.translate(s))
    case _ => println("fail")
  }
}
