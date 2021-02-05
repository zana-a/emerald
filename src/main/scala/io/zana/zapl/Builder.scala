package io.zana.zapl

import io.zana.zapl.parser.Base
import io.zana.zapl.structure.program.Program

import scala.io.Source

object Builder {

  object Parser {

    def parse(input: String, parser: Base.Parser[Any]): Any = {
      Base.parse(parser, input) match {
        case Base.Success(s, _) => s
        case Base.Failure(f, _) => throw new Error(s"Failure: $f")
        case Base.Error(e, _) => throw new Error(s"Error: $e")
      }
    }
  }

  def fromFile(args: Array[String]): String = {
    args.length match {
      case 0 => "Please provide an input."
      case 1 | _ => {
        val io = Source.fromFile(args.head)
        val input = io.mkString
        io.close

        val ast = try {
          Parser.parse(input, parser.program.Program.program)
        } catch {
          case error: Error => println(error)
        }

        translator
          .program
          .Program
          .translate(ast.asInstanceOf[Program])
      }
    }
  }
}

