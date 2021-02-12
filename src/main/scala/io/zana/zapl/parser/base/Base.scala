package io.zana.zapl.parser.base

import io.zana.zapl.parser.keyword.Keyword.nonSymbol
import io.zana.zapl.structure.common.Identifier

import scala.util.parsing.combinator.JavaTokenParsers

object Base extends JavaTokenParsers {

  def identifier: Parser[Identifier] =
    not(nonSymbol) ~> super.ident ^^ (id => Identifier(id))
}
