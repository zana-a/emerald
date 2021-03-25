package io.zana.zparser


import ZParser._

object Demo extends App {

  val res =
    parse(
      or(
        or(
          tag("hello"),
          tag("hi")
        ),
        tag("bonjour")
      )
    )("wagwan")
  print(res)
}
