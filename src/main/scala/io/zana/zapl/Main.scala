package io.zana.zapl

import io.zana.zapl.interactive.Repl
import io.zana.zapl.parser.Parser

import scala.io.Source

object Main extends App {
  println(
    Parser.parseAll(
      Parser.program,
      Source.fromFile("demo/example.zapl").mkString
    )
  )
}