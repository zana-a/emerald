package io.zana.zapl.parser

import io.zana.zapl.parser.Keyword._
import io.zana.zapl.structure.Identifier

import scala.util.parsing.combinator.JavaTokenParsers

object Base extends JavaTokenParsers {

  def identifier: Parser[Identifier] = {
    not(keywords) ~> super.ident ^^ {
      case id => Identifier(id)
    }
  }
}
