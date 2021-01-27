package io.zana.zapl

import io.zana.zapl.parser.{Base, Primitive, Program}

object Main {

  val source = {
    """
			|mod A do
			|  def add(a) = do
			|    a + b
			|  end
			|end
			|
			|A::add(1)
			|""".stripMargin
  }

  def main(args: Array[String]): Unit = {
    println(Base.parseAll(Program.build, source))
  }
}
