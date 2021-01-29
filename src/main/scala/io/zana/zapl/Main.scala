package io.zana.zapl

import io.zana.zapl.Runner

import scala.util.parsing.combinator._

object Main extends App {

  println(
    Runner.fromFile("demo/main.zapl")
  )
}
