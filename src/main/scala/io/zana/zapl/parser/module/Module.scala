package io.zana.zapl.parser.module

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.comment.LineComment
import io.zana.zapl.parser.function.Function
import io.zana.zapl.parser.identifier.Identifier
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.module.{Module => Structure}

object Module extends Parsable[Structure] {

  override def apply: Parser[Structure] = {
    val id = MOD ~> Identifier.apply

    val body = DO ~> rep(
      LineComment.apply
        | Function.apply
        | Module.apply
    ) <~ END

    id ~ body ^^ { case id ~ body => Structure(id, body) }
  }
}
