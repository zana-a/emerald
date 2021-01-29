package io.zana.zapl

import io.zana.zapl.parser.{Base, Program}

import java.net.URI
import scala.io.Source

object Runner {

  def fromFile(path: String) = {
    parse(Source.fromFile(path).mkString)
  }

  def fromSource(input: String) = {
    parse(input)
  }

  private def parse(input: String) = {
    Base.parseAll(Program.build, input)
  }
}
