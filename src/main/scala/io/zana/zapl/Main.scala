package io.zana.zapl

import io.zana.zapl.parser.Parser._

object Main {

  final val sampleCode: String =
    """
      |aNumber := 32
      |"""
      .stripLeading
      .stripTrailing
      .stripMargin

  def main(args: Array[String]): Unit = {

    println(parse(definition, sampleCode))
  }
}