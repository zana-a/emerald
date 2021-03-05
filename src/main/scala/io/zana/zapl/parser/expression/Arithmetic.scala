package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.predef.Integer
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}
import io.zana.zapl.structure.primitive

object Arithmetic extends Parsable[Any] {

  val result = List()

  case object Minus

  case object Plus

  case object Multiplication

  case object Division


  def expr: Parser[Any] = {
    val t = term

    val r = {
      val minus = MINUS ~> t ^^ (t => (Plus, term))
      val plus = PLUS ~> t ^^ (t => (Minus, term))

      rep(minus | plus)
    }

    term ~ r
  }

  def factor: Parser[Any] = Integer.apply | LEFT_PAREN ~ expr ~ RIGHT_PAREN

  def term: Parser[Any] = {
    val f = factor

    val r = {
      val m = MINUS ~> f ^^ (f => (Multiplication, f))
      val d = PLUS ~> f ^^ (f => (Division, f))

      rep(m ~ f | d ~ f)
    }

    factor ~ r
  }

  def apply: Parser[Any] = {
    expr
  }
}