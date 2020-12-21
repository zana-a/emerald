package io.zana.zapl.playground

import scala.io.{Source, StdIn}
import scala.util.matching.Regex
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}

object ParserPlayground extends App {

  //  object Parser extends JavaTokenParsers {
  //
  //    override def skipWhitespace: Boolean = false
  //
  //
  //    def module: Parser[Any] = {
  //      def moduleIdent: Parser[String] = "[A-Z]+".r ~ opt(ident) ^^ {
  //        case first ~ optAll => optAll match {
  //          case Some(all) => first ++ all
  //          case None => first
  //        }
  //      }
  //
  //      def moduleBlock: Parser[Any] = phrase("do" ~ whiteSpace ~ opt(rep(variable)) ~ whiteSpace ~ "end")
  //
  //      phrase(("mod" ~ whiteSpace) ~> moduleIdent ~ (whiteSpace ~> moduleBlock)) ^^ {
  //        case name ~ body =>
  //          println(s"module of ${name}: ${body}")
  //      }
  //    }
  //
  //    def `type`: Parser[Any] = wholeNumber
  //
  //    def variable: Parser[Any] = {
  //      (ident ~ opt(whiteSpace) ~
  //        "=" ~ opt(whiteSpace) ~ `type` ~ whiteSpace)
  //    }
  //
  //    def program: Parser[Any] = opt(whiteSpace) ~ phrase(opt(rep(module))) ~ opt(whiteSpace)
  //  }

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
      phrase(ident ~ "=" ~ `type` ~ "\n")
    }

    def block: Parser[Any] = "do" ~ opt(variable) ~ "end"

    def function: Parser[Any] = "def" ~ ident ~ "(" ~ opt(rep(ident)) ~ ")" ~ block

    def program: Parser[Any] = opt(rep(module))
  }


  {
    import Parser._

    println(parseAll(program, Source.fromFile("demo/example.zapl").mkString))
  }
}


//def moduleIdent: Parser[String] = "[A-Z]+".r ~ opt(ident) ^^ {
//  case first ~ optAll => optAll match {
//  case Some(all) => first ++ all
//  case None => first
//}
//}
