package io.zana.zapl.parser

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.common._

import scala.util.parsing.combinator._

object Base extends JavaTokenParsers {

  def identifier: Parser[Identifier] =
    not(nonSymbol) ~> super.ident ^^ (id => Identifier(id))
}
