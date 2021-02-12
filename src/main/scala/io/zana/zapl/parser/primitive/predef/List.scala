package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.primitive.{List => Structure}

object List extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    "[" ~> repsep(Primitive.apply, COMMA) <~ "]" ^^ (result => Structure(result))

}
