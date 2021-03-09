package io.zana.zapl.parser.expression

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.expression.{Expression => Structure}

object Expression extends Parsable[Any] {

  def expr: Parser[Any] = {
    term ~ rep("+" ~ term | "-" ~ term)
  }

  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)

  def factor: Parser[Any] = wholeNumber | "(" ~ expr ~ ")"

  override def apply: Parser[Any] = phrase(expr)
}
