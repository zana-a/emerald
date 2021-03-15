package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.expression.predef._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.parser
import io.zana.zapl.structure
import io.zana.zapl.structure.expression.{Expression => Structure}

object Expression extends Parsable[Any] {

  def number: Parser[Literal] = wholeNumber ^^ (n => Literal(Conversion.toInt(n)))

  def variable: Parser[Variable] = ident ^^ (i => Variable(i))

  def call: Parser[predef.Call] =
    parser.call.Call.apply ^^ {
      case structure.call.FunctionCall(name, params) =>
        predef.FunctionCall(name, params)
      case structure.call.ModuleCall(modules, caller) =>
        predef.ModuleCall(modules, caller.asInstanceOf[predef.FunctionCall])
    }

  def relop: Parser[(Expr, Expr) => Expr] =
    ("==" | "!=" | "<=" | "<" | ">=" | ">" | "<=") ^^ {
      case "==" => (e1, e2) => Pair("==", e1, e2)
      case "!=" => (e1, e2) => Pair("!=", e1, e2)
      case "<=" => (e1, e2) => Pair("<=", e1, e2)
      case "<" => (e1, e2) => Pair("<", e1, e2)
      case ">=" => (e1, e2) => Pair(">=", e1, e2)
      case ">" => (e1, e2) => Pair(">", e1, e2)
    }

  def addop: Parser[(Expr, Expr) => Expr] = ("+" | "-") ^^ {
    case "+" => (e1, e2) => Pair("+", e1, e2)
    case "-" => (e1, e2) => Pair("-", e1, e2)
  }

  def mulop: Parser[(Expr, Expr) => Expr] =
    ("*" | "/" | "%") ^^ {
      case "*" => (e1, e2) => Pair("*", e1, e2)
      case "/" => (e1, e2) => Pair("/", e1, e2)
      case "%" => (e1, e2) => Pair("%", e1, e2)
    }

  def negop: Parser[Expr => Expr] = "-" ^^ (_ => e => Single("-", e))

  def andop: Parser[(Expr, Expr) => Expr] = "&&" ^^ (_ => (e1, e2) => Pair("&&", e1, e2))

  def orop: Parser[(Expr, Expr) => Expr] = "||" ^^ (_ => (e1, e2) => Pair("||", e1, e2))

  def notop: Parser[Expr => Expr] = "!" ^^ (_ => e => Single("!", e))

  def expr: Parser[Expr] = chainl1(orTerm, orTerm, orop)

  def orTerm: Parser[Expr] = chainl1(andTerm, andTerm, andop)

  def andTerm: Parser[Expr] = chainl1(relTerm, relTerm, relop)

  def relTerm: Parser[Expr] = chainl1(addTerm, addTerm, addop)

  def addTerm: Parser[Expr] = chainl1(factor, factor, mulop)

  def singular: Parser[Expr] = (negop | notop) ~ factor ^^ {
    case f ~ e => f(e)
  }

  def factor: Parser[Expr] = singular | call | number | variable | "(" ~> expr <~ ")"

  override def apply: Parser[Any] = expr
}
