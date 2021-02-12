package io.zana.zapl.parser.block

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.comment.LineComment
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.block.{Block => Structure}

object Block extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    DO ~> rep(
      LineComment
        | variable
        | call
        | expression
        | control
    ) <~ END ^^ (values => Structure(values))
  }
}
