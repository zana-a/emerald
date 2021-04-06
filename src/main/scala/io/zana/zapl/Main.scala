package io.zana.zapl

import org.scalafmt.interfaces.Scalafmt

import java.io.File
import java.nio.file.{Files, Path}

object Main extends App {

  if (args.length > 0) {
    if (
      Files.exists(Path.of(args.head)) &&
        Files.isRegularFile(Path.of(args.head)) &&
        args.head.endsWith(".zapl")
    ) {
      val input = Files.lines(Path.of(args.head)).toArray.mkString("\n")

      val ast = parser.base.Base.parse(parser.program.Program.apply, input) match {
        case parser.base.Base.Success(s, _) => s
        case e => throw new Error(e.toString)
      }

      val result = try {
        translator.program.Program.apply(ast)
      } catch {
        case e: Error => standard.Error(e)
      }

      {
        val parent = Path.of(args.head).toAbsolutePath.getParent + "/target/"
        val file = Path.of(args.head).getFileName.toString.dropRight(5) + ".scala"
        val complete = Path.of(parent + file).toAbsolutePath

        if (Files.isRegularFile(complete)) {
          Files.write(complete, result.getBytes)
        } else {
          Files.createDirectory(complete.getParent)
          Files.createFile(complete)
          Files.write(complete, result.getBytes)
        }
      }
    }
  }

}