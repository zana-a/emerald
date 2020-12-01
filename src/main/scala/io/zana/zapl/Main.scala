package io.zana.zapl

import io.zana.zapl.generator.Generator.Generate
import io.zana.zapl.parser.Parser._

object Main {

  final val sampleCode: String =
    """
      |aNumber := "hello world"
      |"""
      .stripLeading
      .stripTrailing
      .stripMargin

  def main(args: Array[String]): Unit = {

    parse(definition, sampleCode) match {
      case Success(result, _) => {
        println("AST:\n" + result)
        println
        println("Scala Code:\n" + Generate.definition(result))
      }
      case Failure(msg, _) => println(msg)
      case Error(msg, _) => println(msg)
    }

  }
}