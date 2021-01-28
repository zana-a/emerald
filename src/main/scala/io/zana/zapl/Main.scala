package io.zana.zapl

import io.zana.zapl.parser.{Base, Primitive, Program}

object Main {

  val source = {
    """
      |mod A do
      |  def add(a, b) = do
      |    a + b
      |    a = []
      |  end
      |
      |  def sub(a, b) = do
      |    a - b
      |  end
      |end
      |""".stripMargin
  }

  def main(args: Array[String]): Unit = {
    println(Base.parseAll(Program.build, source))
  }
}
