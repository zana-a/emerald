package io.zana.zapl.parser.control

import io.zana.zapl.parser
import io.zana.zapl.parser.expression.Logic

object Control {

  import parser.Base._
  import Keyword._
  import parser.block.Block._
  import parser.expression.Expression._
  import parser.primitive.Primitive._

  def guard: Parser[Any] =
    Logic.expression

  def command: Parser[Any] =
    identifier | `type` | expression | block

  def arm: Parser[Any] =
    guard ~ FAT_ARROW ~ command

  def default: Parser[Any] =
    Keyword.UNDERSCORE ~ FAT_ARROW ~ command

  def cond: Parser[Any] =
    COND ~ DO ~ opt(rep(arm)) ~ default ~ END

  def loop: Parser[Any] =
    LOOP ~ DO ~ opt(rep(arm)) ~ default ~ END

  def control: Parser[Any] =
    cond | loop
}
