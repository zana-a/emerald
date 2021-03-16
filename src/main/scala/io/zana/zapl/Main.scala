package io.zana.zapl

object Main extends App {
  Compiler(args, display = true)
  //
  //  println(io.zana.zapl.parser.base.Base.parse(
  //    io.zana.zapl.parser.control.Control.apply,
  //    """
  //      |cond do
  //      |  _ => cond do
  //      |
  //      |  end
  //      |end
  //      |"""
  //      .stripMargin
  //      .trim
  //  ))
}

