package io.zana.zapl

import scala.util.parsing.combinator._

object Main {

  object DemoParser extends RegexParsers {
    def leftBracket: Parser[String] = "["

    def rightBracket: Parser[String] = "]"

    def item: Parser[String] = "[a-zA-Z0-9]+".r

    def list: Parser[List[Any]] =
      "[" ~> repsep(item, ",") <~ "]"
  }

  def main(args: Array[String]): Unit = {
    val input =
      """
        |[1,2,3]
        |"""
        .stripMargin

    val result = DemoParser.parse(DemoParser.list, input)

    result match {
      case DemoParser.Success(matched, _) => println(matched)
      case DemoParser.Failure(msg, _) => println(s"Failure: $msg")
      case DemoParser.Error(msg, _) => println(s"Error: $msg")
    }
  }
}
