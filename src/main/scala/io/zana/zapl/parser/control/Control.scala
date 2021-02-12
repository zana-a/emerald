package io.zana.zapl.parser.control

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.block.Block
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.expression.Logic
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._

object Control {

  def guard: Parser[Any] =
    Logic.expression

  def command: Parser[Any] =
    identifier | `type` | expression | Block.parse

  def arm: Parser[Any] =
    guard ~ FAT_ARROW ~ command

  def default: Parser[Any] =
    UNDERSCORE ~ FAT_ARROW ~ command

  def cond: Parser[Any] =
    COND ~ DO ~ opt(rep(arm)) ~ default ~ END

  def loop: Parser[Any] =
    LOOP ~ DO ~ opt(rep(arm)) ~ default ~ END

  def control: Parser[Any] =
    cond | loop
}
