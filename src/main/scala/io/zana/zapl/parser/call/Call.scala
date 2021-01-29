package io.zana.zapl.parser.call

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.primitive.Primitive

object Call {

  def call: Parser[Any] =
    moduleFnCall | fnCall

  def fnCallParams: Parser[Any] =
    opt(rep(Primitive.`type` | identifier | fnCall | moduleFnCall))

  def fnCall: Parser[Any] =
    identifier ~ Keyword.LEFT_PARENTHESIS ~ fnCallParams ~ Keyword.RIGHT_PARENTHESIS

  def moduleFnCallIdentifier: Parser[Any] =
    identifier ~ opt(rep(Keyword.BOX ~ identifier))

  def moduleFnCall: Parser[Any] =
    moduleFnCallIdentifier ~ Keyword.BOX ~ fnCall

}
