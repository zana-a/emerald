package io.zana.zapl

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.program.Program

import scala.io.Source

object Runner {

  def fromFile(path: String): Base.ParseResult[Any] = {
    val io = Source.fromFile(path)
    val source = io.mkString
    io.close
    parse(source)
  }

  def fromSource(input: String): Base.ParseResult[Any] = {
    parse(input)
  }

  private def parse(input: String): Base.ParseResult[Any] = {
    Base.parseAll(Program.build, input)
  }
}
