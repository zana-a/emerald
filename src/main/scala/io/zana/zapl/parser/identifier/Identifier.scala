package io.zana.zapl.parser.identifier

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword.nonSymbol
import io.zana.zapl.structure.common.{Identifier => Structure}

object Identifier extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    not(nonSymbol) ~> ident ^^ (id => Structure(id))
}
