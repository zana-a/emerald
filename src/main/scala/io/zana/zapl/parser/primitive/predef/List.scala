package io.zana.zapl.parser.primitive.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.primitive.{List => Structure}
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.identifier.Identifier

object List extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    "[" ~> repsep((Primitive.apply | Call.apply | Identifier.apply), COMMA) <~ "]" ^^
      (result => Structure(result))

}
