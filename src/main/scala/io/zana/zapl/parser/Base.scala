package io.zana.zapl
package parser

import scala.util.parsing.combinator.JavaTokenParsers

trait Base extends JavaTokenParsers with Keyword {

  def keywords: Parser[String] =
    TRUE | FALSE | DEF | DO | END | IF | WHILE | MOD

  override def ident: Parser[String] = not(keywords) ~> super.ident

  def identifier: Parser[String] = ident
}
