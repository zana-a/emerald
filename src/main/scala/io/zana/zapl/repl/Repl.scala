package io.zana.zapl.repl

import io.zana.zapl.parser.{Parser, RefinedParser}
import io.zana.zapl.translator.Translator

import scala.io.StdIn

object Repl {

  def start: Unit = {
    while (true) {
      val input = StdIn.readLine("zapl>")

      Parser.parse(Parser.definition, input) match {
        case Parser.Success(result, _) => println(Parser.parse(Parser.definition, input))
        case Parser.Failure(msg, _) => println("Fail:" + msg)
        case Parser.Error(msg, _) => throw new Error("Erorr:" + msg)
      }

      start
    }
  }
}
