package io.zana.zapl.parser

import io.zana.zapl.parser.Base._

object Keyword {


  val TRUE = "true"

  val FALSE = "false"

  val DEF = "def"

  val DO = "do"

  val END = "end"

  val IF = "if"

  val WHILE = "while"

  val MOD = "mod"

  def keywords: Parser[String] =
    TRUE | FALSE | DEF | DO | END | IF | WHILE | MOD

}
