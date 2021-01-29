package io.zana.zapl.parser.module

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.function.Function._
import io.zana.zapl.structure

object Module {

  def module: Parser[structure.module.Module] =
    (Keyword.MOD ~> identifier) ~
      ((Keyword.DO ~> rep(singleLineComment | function | module))
        <~ Keyword.END) ^^ {
      case id ~ result => structure.module.Module(
        id,
        result
      )
    }
}
