package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.block.Block
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.util.Parsable

object Command extends Parsable[Any] {

  override def apply: Parser[Any] =
    Identifier.apply | Primitive.apply | Expression.apply | Block.apply

}
