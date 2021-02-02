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

  def condition: Parser[Any] =
    guard ~ FAT_ARROW ~ command

  def defaultCondition: Parser[Any] =
    Keyword.UNDERSCORE ~ FAT_ARROW ~ command

  def `if`: Parser[Any] =
    IF ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

  def `while`: Parser[Any] =
    WHILE ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

  def control: Parser[Any] =
    `if` | `while`
}
