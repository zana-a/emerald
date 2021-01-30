package io.zana.zapl

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.call.Call

object Main extends App {

  println(
    //    Runner.fromFile("demo/main.zapl")
    Base.parse(
      Call.call,
      """
        |f(true, a())
        |"""
        .stripMargin
        .trim
    )
  )
}
