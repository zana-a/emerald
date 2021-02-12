package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.block.Block
import io.zana.zapl.parser.primitive.Primitive

object Command extends Parsable[Any] {

  override def apply: Parser[Any] =
    identifier | Primitive.`type` | expression | Block.apply

}
