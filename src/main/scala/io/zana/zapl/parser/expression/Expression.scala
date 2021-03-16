package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.expression.predef._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.parser
import io.zana.zapl.structure
import io.zana.zapl.structure.expression.{Call, Expression, Pair, Single, Variable}

object Expression extends Parsable[Expression] {

  def number: Parser[Expression] = wholeNumber ^^ (n => structure.primitive.Integer(Conversion.toInt(n)))

  def variable: Parser[Expression] = ident ^^ {
    case "true" => structure.primitive.Boolean(true)
    case "false" => structure.primitive.Boolean(false)
    case i => Variable(i)
  }

  def call: Parser[Call] =
    parser.call.Call.apply

  def relop: Parser[(Expression, Expression) => Expression] =
    ("==" | "!=" | "<=" | "<" | ">=" | ">" | "<=") ^^ {
      case "==" => (e1, e2) => Pair("==", e1, e2)
      case "!=" => (e1, e2) => Pair("!=", e1, e2)
      case "<=" => (e1, e2) => Pair("<=", e1, e2)
      case "<" => (e1, e2) => Pair("<", e1, e2)
      case ">=" => (e1, e2) => Pair(">=", e1, e2)
      case ">" => (e1, e2) => Pair(">", e1, e2)
    }

  def addop: Parser[(Expression, Expression) => Expression] = ("+" | "-") ^^ {
    case "+" => (e1, e2) => Pair("+", e1, e2)
    case "-" => (e1, e2) => Pair("-", e1, e2)
  }

  def mulop: Parser[(Expression, Expression) => Expression] =
    ("*" | "/" | "%") ^^ {
      case "*" => (e1, e2) => Pair("*", e1, e2)
      case "/" => (e1, e2) => Pair("/", e1, e2)
      case "%" => (e1, e2) => Pair("%", e1, e2)
    }

  def negop: Parser[Expression => Expression] = "-" ^^ (_ => e => Single("-", e))

  def andop: Parser[(Expression, Expression) => Expression] = "&&" ^^ (_ => (e1, e2) => Pair("&&", e1, e2))

  def orop: Parser[(Expression, Expression) => Expression] = "||" ^^ (_ => (e1, e2) => Pair("||", e1, e2))

  def notop: Parser[Expression => Expression] = "!" ^^ (_ => e => Single("!", e))

  def expr: Parser[Expression] = chainl1(orTerm, orTerm, orop)

  def orTerm: Parser[Expression] = chainl1(andTerm, andTerm, andop)

  def andTerm: Parser[Expression] = chainl1(relTerm, relTerm, relop)

  def relTerm: Parser[Expression] = chainl1(addTerm, addTerm, addop)

  def addTerm: Parser[Expression] = chainl1(factor, factor, mulop)

  def singular: Parser[Expression] = (negop | notop) ~ factor ^^ {
    case f ~ e => f(e)
  }

  def factor: Parser[Expression] = singular | call | number | variable | "(" ~> expr <~ ")"

  override def apply: Parser[Expression] = expr
}
