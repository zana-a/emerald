package io.zana.zapl

import java.nio.file.Path
import scala.io.Source

object Main extends App {

  val parse = parser.base.Base.parse(
    parser.program.Program.apply,
    {
      val io = Source.fromFile(Path.of("demo/main.zapl").toUri)
      val source = io.mkString
      io.close()
      source
    }
  )

  parse match {
    case parser.base.Base.Success(s, _) =>
      println(s)
      pprint.pprintln(s, height = 9000, width = 2)

    case parser.base.Base.NoSuccess(s, _) =>
      throw new RuntimeException(s)
  }

}