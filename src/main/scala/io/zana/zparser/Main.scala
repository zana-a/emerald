package io.zana.zparser

object Main extends App {

  import ZParser._

  println(
    parse(alpha | digit)("123abc")
  )

}
