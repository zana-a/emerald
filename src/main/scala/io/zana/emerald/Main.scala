package io.zana.emerald

import java.nio.file.{Files, Path}

import scala.io.Source

object Main {


  def main(args: Array[String]): Unit = {

    if (args.length.isNaN) {
      val input = args(0)

      if (Files.exists(Path.of(input))) {
        val input = Source.fromFile(args(0))
        for (line <- input) {
          print(line)
        }
        input.close()
      } else {
        print(s"The file: ${args(0)} was not found")
      }
    } else {
      print("Please provide a file to run")
    }

  }
}
