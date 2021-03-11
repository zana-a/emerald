package io.zana.zapl

import io.zana.zapl.structure.program.Program

import java.io.FileNotFoundException
import java.nio.file.Path
import scala.io.Source

case class Compiler(input: String, display: Boolean = false) {

  private val path: Path = Path.of(input)

  private val source: String = {
    val io = Source.fromFile(path.toUri)
    if (path.toFile.exists) {
      val source = io.mkString
      io.close()
      source
    } else {
      throw new FileNotFoundException(s"The path ($path) could not be found.")
    }
  }

  private val ast: Program = {
    parser.base.Base.parse(parser.program.Program.apply, source) match {
      case parser.base.Base.Success(s, _) => s
      case parser.base.Base.Failure(s, _) => throw new RuntimeException(s)
      case parser.base.Base.Error(s, _) => throw new RuntimeException(s)
    }
  }

  private val translate: String = {
    translator.program.Program.apply(ast)
  }


  if (display) {
    def standardOut(heading: String, input: Any): Unit = {
      println(heading + "\n" + "-" * heading.length)
      pprint.pprintln(input, height = 9000, width = 2)
      println
    }

    standardOut("AST", ast)
    standardOut("Translator", translate)
  }
}


