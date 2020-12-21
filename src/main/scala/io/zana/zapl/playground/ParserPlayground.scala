package io.zana.zapl.playground

import scala.io.{Source, StdIn}
import scala.util.matching.Regex
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}

object ParserPlayground extends App {

  object Parser extends JavaTokenParsers {

    def module: Parser[Any] = {
      def moduleIdent: Parser[String] = """^([A-Z])(\w?)+(\d?)+""".r

      "mod" ~ moduleIdent ~ "do" ~ opt(rep(function)) ~ "end" ^^ {
        case _ ~ name ~ _ ~ body ~ _ =>
          println(s"module of ${name}: ${body}")
      }
    }

    def `type`: Parser[Any] = wholeNumber

    def variable: Parser[Any] = {
      ident ~ "=" ~ `type`
    }

    def block: Parser[Any] = {
      def blockBody: Parser[Any] = opt(rep(variable | function | block))

      "do" ~ blockBody ~ "end"
    }

    def function: Parser[Any] = "def" ~ ident ~ "(" ~ opt(repsep(ident, ",")) ~ ")" ~ block

    def program: Parser[Any] = opt(rep(module | function | block | variable))
  }


  import Parser._

  println(parseAll(program, Source.fromFile("demo/example.zapl").mkString))

}


//def moduleIdent: Parser[String] = "[A-Z]+".r ~ opt(ident) ^^ {
//  case first ~ optAll => optAll match {
//  case Some(all) => first ++ all
//  case None => first
//}
//}
