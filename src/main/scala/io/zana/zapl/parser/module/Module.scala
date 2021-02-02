package io.zana.zapl.parser.module

import io.zana.zapl.{parser, structure}

object Module {

  import parser.Base._
  import Keyword._
  import parser.comment.Comment._
  import parser.function.Function._
  import structure.module.{Module => Result}

  def module: Parser[Result] = {
    val id = MOD ~> identifier
    val body = DO ~> rep(lineComment | function | module) <~ END

    id ~ body ^^ { case id ~ body => Result(id, body) }
  }
}
