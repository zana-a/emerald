package io.zana.zapl

import io.zana.zapl.parser.Parser
import io.zana.zapl.repl.Repl
import io.zana.zapl.translator.Translator.Generate

object Main {

  def main(args: Array[String]): Unit = {
    Repl.start
  }
}