package io.zana.zapl

import pprint.pprintln

object Main {

  def main(args: Array[String]): Unit = {

    if (args.length > 0) {
      pprintln(
        Runner.fromFile(args(0)),
        width = 2,
        indent = 2,
      )
    } else {
      println("No input given")
    }
  }
}

