package io.zana.zapl

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.variable.{Variable => Parser}
import io.zana.zapl.structure.variable.{Variable => Structure}

object Main extends App {

  val input =
    """
      |items = ["app\"le\"", "banana", "orange", true, false, [[1234]]]
      |""".stripMargin

  def build(parser: Base.Parser[Any], input: String): String = {
    Base.parse(parser, input) match {
      case Base.Success(s, _) =>
        translator.variable.Variable.translate(s.asInstanceOf[Structure])
      case Base.Failure(f, _) => s"Failure: $f"
      case Base.Error(e, _) => s"Error: $e"
    }
  }

  println(build(Parser.variable, input))
}
