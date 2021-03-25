package io.zana.zparser


import ZParser._

object Demo extends App {

  val res =
    parse(or(tag("a"), tag("d")))("aef")
  print(res)
}
