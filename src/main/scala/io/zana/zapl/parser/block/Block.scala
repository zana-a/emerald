package io.zana.zapl.parser.block

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.comment.LineComment
import io.zana.zapl.parser.control.Control
import io.zana.zapl.parser.expression.Expression
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.parser.variable.Variable
import io.zana.zapl.structure.block.{Block => Structure}

object Block extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    DO ~> rep(
      LineComment.apply
        | Variable.apply
        | Call.apply
        | Expression.apply
        | Control.apply
    ) <~ END ^^ (values => Structure(values))
  }
}
