package io.zana.zapl.repl

import io.zana.zapl.parser.Parser
import io.zana.zapl.translator.Translator

import scala.io.StdIn

object Repl {

  def start: Unit = {
    while (true) {
      val input = StdIn.readLine(">>>")

      Parser.parse(Parser.definition, input) match {
        case Parser.Success(result, _) => println(Translator.Generate.definition(result))
        case Parser.Failure(msg, _) => println("Fail:" + msg)
        case Parser.Error(msg, _) => throw new Error("Erorr:" + msg)
      }

      start
    }
  }
}
