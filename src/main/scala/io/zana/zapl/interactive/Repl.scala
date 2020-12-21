package io.zana.zapl.interactive

import io.zana.zapl.parser.Parser
import io.zana.zapl.translator.Translator

import scala.io.StdIn

object Repl {

  def loop: Unit = {
    val input = StdIn.readLine(">")
    if (input != "::exit") {
      Parser.parseAll(Parser.program, input) match {
        case Parser.Success(result, _) => println(result)
        case Parser.Failure(msg, _) => println(s"Fail: ${msg}")
        case Parser.Error(msg, _) => throw new Error(s"Erorr: ${msg}")
      }
      loop
    } else {
      println("Goodbye!")
    }
  }

  def start: Unit = {
    loop
  }
}
