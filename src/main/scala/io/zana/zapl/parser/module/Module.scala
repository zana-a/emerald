package io.zana.zapl.parser.module

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.comment.Comment._
import io.zana.zapl.parser.function.Function._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.module.{Module => Structure}

object Module {

  def module: Parser[Structure] = {
    val id = MOD ~> identifier
    val body = DO ~> rep(lineComment | function | module) <~ END

    id ~ body ^^ { case id ~ body => Structure(id, body) }
  }
}
