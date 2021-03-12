package io.zana.zapl.standard

import java.nio.file.Path
import scala.sys.exit
import scala.io.AnsiColor._

object Error {

  def apply(msg: Error): Nothing = {
    val tag = s"$BOLD${RED}Error: $RESET"
    println(s"$tag$msg")
    exit
  }

  case class Custom(msg: String) extends Error {
    override def toString: String = msg
  }

  case class FileNotFound(path: String) extends Error {
    val uri: String = Path.of("").toUri.toString + path
    val msg: String = s"The path to ($uri) was not a valid path."

    override def toString: String = msg
  }

  case class UnknownSyntax(msg: String) extends Error {
    override def toString: String =
      s"An unknown syntax error occurred.\n${" " * "error: ".size}$msg"
  }

}
