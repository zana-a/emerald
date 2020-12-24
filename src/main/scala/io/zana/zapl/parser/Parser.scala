package io.zana.zapl.parser

import io.zana.zapl.structure.{Auxillery, Statement, Type}

object Parser extends Base {

  protected object Keyword {
    val DO = "do"

    val END = "end"

    val MOD = "mod"

    val TRUE = "true"

    val FALSE = "false"

    val LOOP = "loop"

    val COND = "cond"
  }

  protected def keywords: Parser[String] = {
    import Keyword._

    DO | END | MOD | TRUE | FALSE | COND | LOOP
  }

  //  ----------------------

  override def ident: Parser[String] = {
    not(keywords) ~> super.ident
  }

  //  ----------------------

  object Primitive {

    def boolean: Parser[Type.Boolean] = {
      def t: Parser[Type.Boolean] = Keyword.TRUE ^^ {
        result => Type.Boolean(result.toBoolean)
      }

      def f: Parser[Type.Boolean] = Keyword.FALSE ^^ {
        result => Type.Boolean(result.toBoolean)
      }

      t | f ^^ (result => result)
    }

    def string: Parser[Type.String] = {
      stringLiteral ^^ (result => Type.String(result))
    }

    def list: Parser[Type.List] = {
      "[" ~> repsep(`type`, ",") <~ "]" ^^ {
        result => Type.List(result)
      }
    }

    def integer: Parser[Type.Integer] = wholeNumber ^^ {
      result => Type.Integer(result.toInt)
    }

    def `type`: Parser[Type.Type[_]] = string | integer | list | boolean
  }

  //  ----------------------

  object Module {
    def identifier: Parser[Auxillery.Identifier] =
      """^([A-Z])(\w?)+(\d?)+""".r ^^ (result => Auxillery.Identifier(result))

    def module: Parser[Statement.Module] =
      ("mod" ~> identifier) ~
        ("do" ~> opt(rep(Function.function | module)) <~ "end") ^^ {
        case identifier ~ body => body match {
          case Some(items) => Statement.Module(identifier, items)
          case None => Statement.Module(identifier, List())
        }
      }
  }

  object Variable {
    def variable: Parser[Statement.Variable] =
      ident ~ "=" ~ ??? ^^ {
        ???
      }
  }

  object Function {
    def function: Parser[Statement.Function] =
      "def" ~ ident ~ "(" ~ opt(repsep(ident, ",")) ~ ")" ~ "=" ~ ??? ^^ {
        ???
      }
  }

  object Block {
    def block: Parser[Statement.Block] =
      ???
  }

  object Control {
    def guard: Parser[Statement.Control.Guard] =
      ???

    def command: Parser[Statement.Control.Command] =
      ???

    def guardCommand: Parser[Statement.Control.GuardCommand] =
      ???
  }

  object Expression {
    ???
  }

  object Program {

    import io.zana.zapl.structure.Base

    def builder: Parser[Base.Program] = ???
  }

}
