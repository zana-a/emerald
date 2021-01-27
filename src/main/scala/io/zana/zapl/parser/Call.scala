package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Module._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._

object Call {

  def call: Parser[Any] = moduleFnCall | fnCall

  def fnCallParams: Parser[Any] = opt(rep(`type` | identifier | fnCall | moduleFnCall))

  def fnCall: Parser[Any] = identifier ~ LEFT_PARENTHESIS ~ fnCallParams ~ RIGHT_PARENTHESIS

  def moduleFnCallIdentifier: Parser[Any] = identifier ~ opt(rep(BOX ~ identifier))

  def moduleFnCall: Parser[Any] = moduleFnCallIdentifier ~ BOX ~ fnCall

}
