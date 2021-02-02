package io.zana.zapl

import io.zana.zapl.parser.Base
import io.zana.zapl.parser.program.Program
import pprint.pprintln

import scala.io.Source

object Runner {

  private def format(inner: Base.ParseResult[Any]): Unit = pprintln(inner)

  object Parser {

    private def parse(input: String): Base.ParseResult[Any] = {
      Base.parseAll(Program.build, input)
    }

    def fromSource(input: String): Base.ParseResult[Any] = {
      parse(input)
    }

    def fromFile(path: String): Base.ParseResult[Any] = {
      val io = Source.fromFile(path)
      val source = io.mkString
      io.close
      parse(source)
    }
  }

  def runFromFile(args: Array[String]): Unit = {
    args.length match {
      case 0 => println("No input file given!")
      case 1 | _ => format(Parser.fromFile(args(0)))
    }
  }

  def runFromSource(input: String): Unit = {
    format(Parser.fromSource(input))
  }
}
