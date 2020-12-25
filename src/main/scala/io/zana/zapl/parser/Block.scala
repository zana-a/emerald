package io.zana.zapl
package parser

trait Block extends Base
  with Expression
  with Function
  with Control
  with Call {
  def block: Parser[Any] =
    DO ~ (expression | function | control | call) ~ END
}
