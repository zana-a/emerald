package io.zana.zapl.parser

import io.zana.zapl.parser.Keyword._

import scala.util.parsing.combinator.JavaTokenParsers

object Base extends JavaTokenParsers {

  def identifier: Parser[String] = {
    not(keywords) ~> super.ident
  }
}
