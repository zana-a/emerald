package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.primitive.{Boolean => Structure}

object Boolean extends Parsable[Structure] {

  override def apply: Parser[Structure] = {

    def t: Parser[Structure] = TRUE ^^ (result => Structure(result.toBoolean))

    def f: Parser[Structure] = FALSE ^^ (result => Structure(result.toBoolean))

    t | f ^^ (result => result)
  }
}
