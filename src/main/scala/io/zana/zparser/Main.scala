package io.zana.zparser

object Main {

  object MyParser extends ZParser

  import MyParser._

  println(
    parse(tag("abc") | tag("bde"))("abcd")
  )
}
