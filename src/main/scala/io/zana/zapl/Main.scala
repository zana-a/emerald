package io.zana.zapl

object Main {

  def main(args: Array[String]): Unit =
    Compiler(args(0), display = true)
}
