package io.zana.zapl.standard

import io.zana.zapl.structure.program.Program
import io.zana.zapl.{parser, translator}
import org.scalafmt.interfaces.Scalafmt

import java.nio.file.{Files, Path}
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

  def write(): Unit = {

    def sourceGenerator(): (String, Path) = {
      val path = Path.of(args.head)

      if (Files.exists(path)) {
        val newPath = {
          if (path.getFileName.toString.endsWith(".zapl")) {
            Path.of(
              path
                .getParent
                .toAbsolutePath
                .toString ++ "/target/" ++ path.getFileName.toString.dropRight(5) ++ ".scala"
            )
          } else {
            throw new Error("Incorrect File Type")
          }
        }

        def ast =
          parser.base.Base.parse(parser.program.Program.apply, source) match {
            case parser.base.Base.Success(s, _) => s
            case _ => throw new Error
          }

        (translator
          .program
          .Program
          .apply(ast), newPath)
      } else {
        throw new Error
      }
    }

    args.length match {
      case 0 => println("Please provide an input")
      case 1 | _ => {
        val (preformat, newPath) = sourceGenerator()
        val scalafmt = Scalafmt.create(this.getClass.getClassLoader)

        val source = scalafmt.format(
          Path.of("resources/.scalafmt.conf"),
          newPath,
          preformat
        )
        Files.deleteIfExists(newPath)
        Files.createDirectories(newPath.getParent)
        Files.createFile(newPath)
        Files.write(newPath, source.getBytes)
      }
    }
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
