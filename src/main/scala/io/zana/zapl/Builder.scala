package io.zana.zapl

import io.zana.zapl.parser.base.Base
import io.zana.zapl.structure.program.Program
import org.scalafmt.interfaces.Scalafmt

import java.nio.file.{Files, Path}
import scala.io.Source

object Builder {

  object Parser {

    def parse(input: String, parser: Base.Parser[Any]): Any = {
      Base.parse(parser, input) match {
        case Base.Success(s, _) => s
        case Base.Failure(f, _) => println(s"Failure: $f")
        case Base.Error(e, _) => println(s"Error: $e")
      }
    }
  }

  def write(args: Array[String]): Unit = {

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

        val io = Source.fromFile(path.toString)
        val input = io.mkString
        io.close

        val ast = Parser.parse(input, parser.program.Program.program)

        (translator
          .program
          .Program
          .translate(ast.asInstanceOf[Program]), newPath)
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
}

