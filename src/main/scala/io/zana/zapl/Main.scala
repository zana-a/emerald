package io.zana.zapl

import scala.util.parsing.combinator._

object Main {

  def hello(name: String) = s"Hello, $name"

  def main(args: Array[String]): Unit = {
    val greet = hello("Zana")
    print(greet)
  }
}
