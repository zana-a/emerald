package io.zana.zapl.parser.control.predef

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.block.Block
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.primitive.Primitive
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.control.Control
import io.zana.zapl.parser.util.Parsable

object Command extends Parsable[Any] {

  override def apply: Parser[Any] = (
    Expression.apply
      | Identifier.apply
      | Call.apply
      | Control.apply
      | Primitive.apply
      | Block.apply
    )

}
