package io.zana.zapl

import org.scalafmt.interfaces.Scalafmt
import scala.language.postfixOps
import java.io.File
import sys.process._

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

        s"scala $complete" !
      }
    }
  } else {
    println(
      """
        | 8888888888',8888'        .8.          8 888888888o   8 8888
        |        ,8',8888'        .888.         8 8888    `88. 8 8888
        |       ,8',8888'        :88888.        8 8888     `88 8 8888
        |      ,8',8888'        . `88888.       8 8888     ,88 8 8888
        |     ,8',8888'        .8. `88888.      8 8888.   ,88' 8 8888
        |    ,8',8888'        .8`8. `88888.     8 888888888P'  8 8888
        |   ,8',8888'        .8' `8. `88888.    8 8888         8 8888
        |  ,8',8888'        .8'   `8. `88888.   8 8888         8 8888
        | ,8',8888'        .888888888. `88888.  8 8888         8 8888
        |,8',8888888888888.8'       `8. `88888. 8 8888         8 888888888888
        |
        |A programming language on the JVM.
        |
        |Please provide a file to run.
        |""".stripMargin)
  }

}