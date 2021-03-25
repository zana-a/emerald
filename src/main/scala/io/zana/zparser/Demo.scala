package io.zana.zparser


import ZParser._

object Demo extends App {

  val res =
    parse(
      or(tag("let"), tag("a"))
    )("let a = 2")

  print(res)
}


// let a = 2
