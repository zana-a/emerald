package io.zana.zparser

object Main extends App {

  import ZParser._

  println(
    parse(lowerAlpha | digit | upperAlpha)("A1a")
  )

}
