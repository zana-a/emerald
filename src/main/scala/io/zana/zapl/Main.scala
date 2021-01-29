package io.zana.zapl

import io.zana.zapl.Runner

import java.net.URI

object Main extends App {
  println(
    Runner.fromFile("demo/main.zapl")
  )
}
