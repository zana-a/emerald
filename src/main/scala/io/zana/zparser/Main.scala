package io.zana.zparser

object Main extends App {

  object MyParser extends ZParser

  import MyParser._

  println(
    parse(alpha)("abc")
  )
}
