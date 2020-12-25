package io.zana.zapl
package parser

trait Expression extends Base {
  def arith_expression: Parser[Any] = {
    arith_factor ~ opt(rep(
      PLUS ~ arith_expression
        | MINUS ~ arith_expression
        | MULTIPLICATION ~ arith_expression
        | DIVISION ~ arith_expression))
  }

  def arith_factor: Parser[Any] = {
    arith_constant | LEFT_PARENTHESIS ~ arith_expression ~ RIGHT_PARENTHESIS
  }

  def arith_constant: Parser[Any] = integer | identifier

  def logicExpression: Parser[Any] = {
    logic_factor ~ opt(rep(
      AND ~ logicExpression
        | OR ~ logicExpression
        | EQEQ ~ logicExpression
        | NEQ ~ logicExpression
        | LT ~ logicExpression
        | GT ~ logicExpression
        | LTEQ ~ logicExpression
        | GTEQ ~ logicExpression
    ))
  }

  def logic_factor: Parser[Any] = {
    NOT ~ logic_factor |
      logic_constant |
      LEFT_PARENTHESIS ~ logicExpression ~ RIGHT_PARENTHESIS
  }

  def logic_constant: Parser[Any] = {
    arith_expression | boolean | integer | identifier
  }

  def expression: Parser[Any] = {
    logicExpression | arith_expression
  }
}
