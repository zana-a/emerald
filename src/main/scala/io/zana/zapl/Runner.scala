package io.zana.zapl

import pprint.pprintln

import scala.io.Source

object Runner {

  import parser.Base
  import parser.program._

  private def format(r: Base.ParseResult[Any]): Unit = r match {
    case Base.Success(s, _) => pprintln(s, width = 2, height = 1000)
    case Base.Failure(_, _) => println(r)
    case Base.Error(_, _) => println(r)
  }

  object Parser {

    private def parse(input: String,
                      parser: Base.Parser[Any]): Base.ParseResult[Any] =
      Base.parseAll(parser, input)

    def fromSource(input: String,
                   parser: Base.Parser[Any]): Base.ParseResult[Any] =
      parse(input, parser)

    def fromFile(path: String,
                 parser: Base.Parser[Any]): Base.ParseResult[Any] = {
      val io = Source.fromFile(path)
      val source = io.mkString
      io.close
      parse(source, parser)
    }
  }

  def fromFile(args: Array[String],
               parser: Base.Parser[Any] = Program.program): Unit = {
    args.length match {
      case 0 => println("No input file given!")
      case 1 | _ => format(Parser.fromFile(args(0), parser))
    }
  }

  def fromSource(input: String,
                 parser: Base.Parser[Any] = Program.program): Unit =
    format(Parser.fromSource(input, parser))
}
