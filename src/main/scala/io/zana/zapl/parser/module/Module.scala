package io.zana.zapl.parser.module

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.function.Function._
import io.zana.zapl.structure.module.{Module => Result}

object Module {

  def module: Parser[Result] = {
    (Keyword.MOD ~> identifier) ~
      ((Keyword.DO ~>
        rep(singleLineComment
          | function
          | module)) <~ Keyword.END) ^^ {
      case id ~ result => Result(
        id,
        result
      )
    }
  }
}
