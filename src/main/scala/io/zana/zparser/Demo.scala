package io.zana.zparser

object Demo extends App {
  val res = ZParser.parse("d")(
    ZParser.or(
      ZParser.tag("hi"),
      ZParser.tag("hello")
    )
  )
  print(res)
}