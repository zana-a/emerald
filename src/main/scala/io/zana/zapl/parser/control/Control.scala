package io.zana.zapl.parser.control

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.block.Block._
import io.zana.zapl.parser.expression.Expression._
import io.zana.zapl.parser.primitive.Primitive._

object Control {

  def guard: Parser[Any] =
    Logic.expression

  def command: Parser[Any] =
    identifier | `type` | expression | block

  def condition: Parser[Any] =
    guard ~ Keyword.FAT_ARROW ~ command

  def defaultCondition: Parser[Any] =
    Keyword.UNDERSCORE ~ Keyword.FAT_ARROW ~ command

  def `if`: Parser[Any] =
    Keyword.IF ~ Keyword.DO ~ opt(rep(condition)) ~ defaultCondition ~ Keyword.END

  def `while`: Parser[Any] =
    Keyword.WHILE ~ Keyword.DO ~ opt(rep(condition)) ~ defaultCondition ~ Keyword.END

  def control: Parser[Any] =
    `if` | `while`
}
