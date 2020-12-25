package io.zana.zapl
package parser

trait Control extends Base
  with Expression
  with Primitive
  with Keyword
  with Operator {

  def guard: Parser[Any] = logicExpression

  def command: Parser[Any] = identifier | `type` | expression | block

  def condition: Parser[Any] = guard ~ FAT_ARROW ~ command

  def defaultCondition: Parser[Any] = UNDERSCORE ~ FAT_ARROW ~ command

  def `if`: Parser[Any] = IF ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

  def `while`: Parser[Any] = WHILE ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END
}
