package io.zana.zapl
package parser

trait Function extends Base {

  def function: Parser[Any] = {
    DEF ~
      identifier ~ LEFT_PARENTHESIS ~ opt(rep(identifier)) ~ RIGHT_PARENTHESIS ~
      EQ ~ (identifier | `type` | call | control | expression | block)
  }
}
