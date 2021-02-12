package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.structure.primitive.{List => Structure}

object List extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    "[" ~> repsep(`type`, COMMA) <~ "]" ^^ (result => Structure(result))

}
