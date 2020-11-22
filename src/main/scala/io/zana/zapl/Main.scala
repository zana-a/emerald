package io.zana.zapl

import java.nio.file.{Files, Path}

import scala.io.Source
import scala.util.parsing.combinator.JavaTokenParsers

object Main extends App {

  object Program extends JavaTokenParsers with Assignment {
    def main: Parser[Any] = opt(rep(assignment))
  }

  trait Assignment extends JavaTokenParsers with MathExpression {

    def assignment: Parser[String] = identifier ~ ":=" ~ expr ^^ { case a ~ b ~ c => s"$a$b$c" }

    def identifier: Parser[String] = letter ~ rep(letter | number) ^^ { case a ~ b => a.toList.++(b).mkString }

    def letter: Parser[String] = "[a-zA-Z]".r

    def number: Parser[String] = "[1-9]".r
  }

  trait MathExpression extends JavaTokenParsers {

    def expr: Parser[Any] =
      "(" ~> expr <~ ")" ~ operator ~ expr | "(" ~> expr <~ ")" | integer ~ operator ~ expr | integer

    def operator: Parser[String] = "+" | "-" | "*" | "/"

    def integer: Parser[Int] = wholeNumber ^^ {
      _.toInt
    }
  }


  val path = Source.fromFile(Path.of(args(0)).toUri)
  val source = try path.mkString finally path.close()

  if (Files.exists(Path.of(args(0))))
    println(Program.parseAll(Program.main, source))
  else
    print(s"The given path ${args(0)} does not exist!")

}
