package io.zana.zapl.standard

import io.zana.zapl.structure.program.Program
import io.zana.zapl.{parser, translator}

import java.nio.file.Path
import scala.io.Source

case class Compiler(args: Seq[String], display: Boolean = false) {

  private val path: Path = if (args.nonEmpty) {
    Path.of(args.head)
  } else {
    Error(Error.Custom("No input path was given."))
  }

  private val source: String = {
    if (path.toFile.exists) {
      val io = Source.fromFile(path.toUri)
      val source = io.mkString
      io.close()
      source
    } else {
      Error(Error.FileNotFound(path.toString))
    }
  }

  private val ast: Program = {
    parser.base.Base.parse(parser.program.Program.apply, source) match {
      case parser.base.Base.Success(s, _) => s
      case parser.base.Base.Failure(s, _) => Error(Error.UnknownSyntax(s))
      case parser.base.Base.Error(s, _) => Error(Error.UnknownSyntax(s))
    }
  }

  private val translate: String = {
    translator.program.Program.apply(ast)
  }


  if (display) {
    def standardOut(heading: String, input: Any, pretty: Boolean = false): Unit = {
      println(heading + "\n" + "-" * heading.length)
      if (pretty) {
        pprint.pprintln(input, height = 9000, width = 2)
      } else {
        println(input)
      }
    }

    standardOut("AST", ast, pretty = true)
    println
    standardOut("Translator", translate)
  }
}
