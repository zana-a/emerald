package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.expression.predef._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.parser
import io.zana.zapl.parser.primitive
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure
import io.zana.zapl.structure.expression.{Call, Expression, Pair, Single}
import io.zana.zapl.structure.identifier.Identifier

object Expression extends Parsable[Expression] {

  def number: Parser[Expression] = wholeNumber ^^
    (n => structure.primitive.Integer(Conversion.toInt(n)))

  def variable: Parser[Expression] =
    not(DEF | DO | END | COND | LOOP | MOD) ~> ident ^^ {
      case "true" => structure.primitive.Boolean(true)
      case "false" => structure.primitive.Boolean(false)
      case i => Identifier(i)
    }

  def call: Parser[Expression] =
    parser.call.Call.apply

  def relop: Parser[(Expression, Expression) => Expression] =
    (EQEQ | NEQ | LTEQ | LT | GTEQ | GT) ^^ {
      case EQEQ => (e1, e2) => Pair(EQEQ, e1, e2)
      case NEQ => (e1, e2) => Pair(NEQ, e1, e2)
      case LTEQ => (e1, e2) => Pair(LTEQ, e1, e2)
      case LT => (e1, e2) => Pair(LT, e1, e2)
      case GTEQ => (e1, e2) => Pair(GTEQ, e1, e2)
      case GT => (e1, e2) => Pair(GT, e1, e2)
    }

  def addop: Parser[(Expression, Expression) => Expression] =
    (PLUS | MINUS) ^^ {
      case PLUS => (e1, e2) => Pair(PLUS, e1, e2)
      case MINUS => (e1, e2) => Pair(MINUS, e1, e2)
    }

  def mulop: Parser[(Expression, Expression) => Expression] =
    (MULTIPLICATION | DIVISION | MODULO) ^^ {
      case MULTIPLICATION => (e1, e2) => Pair(MULTIPLICATION, e1, e2)
      case DIVISION => (e1, e2) => Pair(DIVISION, e1, e2)
      case MODULO => (e1, e2) => Pair(MODULO, e1, e2)
    }

  def negop: Parser[Expression => Expression] =
    MINUS ^^ (_ => e => Single(MINUS, e))

  def andop: Parser[(Expression, Expression) => Expression] =
    AND ^^ (_ => (e1, e2) => Pair(AND, e1, e2))

  def orop: Parser[(Expression, Expression) => Expression] =
    OR ^^ (_ => (e1, e2) => Pair(OR, e1, e2))

  def notop: Parser[Expression => Expression] =
    BANG ^^ (_ => e => Single(BANG, e))

  def expr: Parser[Expression] =
    chainl1(orTerm, orTerm, orop)

  def orTerm: Parser[Expression] =
    chainl1(andTerm, andTerm, andop)

  def andTerm: Parser[Expression] =
    chainl1(relTerm, relTerm, relop)

  def relTerm: Parser[Expression] =
    chainl1(addTerm, addTerm, addop)

  def addTerm: Parser[Expression] =
    chainl1(factor, factor, mulop)

  def singular: Parser[Expression] =
    (negop | notop) ~ factor ^^ {
      case f ~ e => f(e)
    }

  def factor: Parser[Expression] = (
    singular
      | call
      | number
      | primitive.predef.String.apply
      | variable
      | LEFT_PAREN ~> expr <~ RIGHT_PAREN
    )

  override def apply: Parser[Expression] = expr
}
