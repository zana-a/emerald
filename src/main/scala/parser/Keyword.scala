package io.zana.zapl
package parser

trait Keyword extends Base {
  val DO = "do"

  val END = "end"

  val MOD = "mod"

  val TRUE = "true"

  val FALSE = "false"

  val LOOP = "while"

  val COND = "if"

  def keywords: Parser[String] = {
    DO | END | MOD | TRUE | FALSE | COND | LOOP
  }
}
