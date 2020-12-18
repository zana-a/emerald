package io.zana.zapl.parser

import io.zana.zapl.ast._
import io.zana.zapl.parser.Parser._
import io.zana.zapl.translator.Translator

import scala.util.parsing.combinator.JavaTokenParsers

object RefinedParser extends App {

  object Parser extends JavaTokenParsers {
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
      stringLiteral ^^ {
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

    def statement: Parser[Statement.Statement] = block | definition

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

      "fn" ~ identifier ~ functionArgs ~ block ^^ {
        case _ ~ name ~ functionArgs ~ block =>
          Statement.Function(name, functionArgs, block)
      }
    }

    def module: Parser[Statement.Module] = {
      def moduleIdentifier: Parser[String] = {
        upperAlpha ~ rep(alphanumeric) ^^ {
          case one ~ many => one.concat(many.mkString)
        }
      }

      "mod" ~> moduleIdentifier ~ rep(function) <~ "end" ^^ {
        case name ~ functions => Statement.Module(name, functions)
      }
    }
  }

}
