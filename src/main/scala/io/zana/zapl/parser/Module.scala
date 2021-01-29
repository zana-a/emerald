package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Comment._
import io.zana.zapl.parser.Function._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.structure

object Module {

  def module: Parser[structure.module.Module] =
    (MOD ~> identifier) ~ ((DO ~> rep(singleLineComment | function | module)) <~ END) ^^ {
      case id ~ result => structure.module.Module(
        id,
        result
      )
    }
}
