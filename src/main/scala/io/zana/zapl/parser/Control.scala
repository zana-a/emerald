package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Block._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._

object Control {


	def guard: Parser[Any] = logicExpression

	def command: Parser[Any] = identifier | `type` | expression | block

	def condition: Parser[Any] = guard ~ FAT_ARROW ~ command

	def defaultCondition: Parser[Any] = UNDERSCORE ~ FAT_ARROW ~ command

	def `if`: Parser[Any] = IF ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

	def `while`: Parser[Any] = WHILE ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

	def control: Parser[Any] = `if` | `while`
}