package io.zana.zapl.parser

import io.zana.zapl.ast._
import io.zana.zapl.parser.Parser._
import io.zana.zapl.translator.Translator

import scala.io.Source
import scala.util.matching.Regex
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}

object RefinedParser extends App {

  object Parser extends RegexParsers {
    override def skipWhitespace: Boolean = true


    def lowerAlpha: Parser[String] = "[a-z]".r

    def upperAlpha: Parser[String] = "[A-Z]".r

    def alpha: Parser[String] = lowerAlpha | upperAlpha

    def zero: Parser[String] = "0"

    def numeral: Parser[String] = "[1-9]".r

    def numeric: Parser[String] = zero | numeral

    def alphanumeric: Parser[String] = alpha | numeric

    def identifier: Parser[String] = alpha ~ rep(alphanumeric) ^^ {
      case alpha ~ alphanumeric => alpha + alphanumeric.mkString
    }

    def integer: Parser[Type.Integer] = numeral ~ opt(rep(numeric)) ^^ {
      case numeral ~ opt => opt match {
        case Some(value) => Type.Integer((numeral + value.mkString).toInt)
        case None => Type.Integer(numeral.toInt)
      }
    }

    // TODO: only alphanumeric. what about any char?
    def string: Parser[Type.String] = {
      "[\\\"(\\\\.|[^\"\\\\])*\\\"]".r ^^ {
        case value => Type.String(value)
      }
    }

    def `true`: Parser[Type.Bool] = "true" ^^ {
      case t => Type.Bool(t.toBoolean)
    }

    def `false`: Parser[Type.Bool] = "false" ^^ {
      case t => Type.Bool(t.toBoolean)
    }

    def bool: Parser[Type.Bool] = `true` | `false`

    def list: Parser[Type.List] = {
      "[" ~> repsep(`type`, ",") <~ "]" ^^ {
        case result => Type.List(result)
      }
    }

    def `type`: Parser[Type.Type] = string | integer | bool | list

    def definition: Parser[Statement.Definition] = {
      identifier ~ "=" ~ `type` ^^ {
        case identifier ~ _ ~ t => Statement.Definition(identifier, t)
      }
    }

    //    def expression: Parser[Expression.Expression] =

    def statement: Parser[Statement.Statement] = {
      module | function | block | definition
    }

    def block: Parser[Statement.Block] = {
      "do" ~ opt(rep(statement)) ~ "end" ^^ {
        case _ ~ result ~ _ => result match {
          case Some(value) => Statement.Block(value)
          case None => Statement.Block(List())
        }
      }
    }

    def function: Parser[Statement.Function] = {
      def functionArgs: Parser[List[String]] = {
        "(" ~> opt(repsep(identifier, ",")) <~ ")" ^^ {
          case result => result match {
            case Some(args) => args
            case None => List()
          }
        }
      }

      "fn" ~> identifier ~ functionArgs ~ block ^^ {
        case name ~ functionArgs ~ block =>
          Statement.Function(name, functionArgs, block)
      }
    }

    def module: Parser[Statement.Module] = {
      def moduleIdentifier: Parser[String] = {
        upperAlpha ~ rep(alphanumeric) ^^ {
          case one ~ many => one.concat(many.mkString)
        }
      }

      def moduleBlock: Parser[Statement.ModuleBlock] = {
        "do" ~> opt(rep(function)) <~ "end" ^^ {
          case result => result match {
            case Some(functions) => Statement.ModuleBlock(functions)
            case None => Statement.ModuleBlock(List())
          }
        }
      }

      phrase("mod" ~> moduleIdentifier ~ moduleBlock) ^^ {
        //        case name ~ functions => functions match {
        //          case Some(list) => Statement.Module(name, list)
        //          case None => Statement.Module(name, List())
        //        }
        case name ~ block => Statement.Module(name, block)
      }
    }

    def program: Parser[Any] = rep(opt(module)) ^^ {
      case res => println(res)
    }
  }

  val source = Source.fromFile("demo/example.zapl").mkString
  //  println(source)

  println(Parser.parse(Parser.program,
    """
      |mod A do
      |
      |end
      |""".stripMargin))
}
