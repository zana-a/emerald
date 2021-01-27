package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._

object Expression {

  def arithExpression: Parser[Any] = {
    arithFactor ~ opt(rep(
      PLUS ~ arithExpression
        | MINUS ~ arithExpression
        | MULTIPLICATION ~ arithExpression
        | DIVISION ~ arithExpression))
  }


  def arithFactor: Parser[Any] = {
    arithConstant | LEFT_PARENTHESIS ~ arithExpression ~ RIGHT_PARENTHESIS
  }

  def arithConstant: Parser[Any] = integer | identifier

  def logicExpression: Parser[Any] = {
    logicFactor ~ opt(rep(
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

  def logicFactor: Parser[Any] = {
    NOT ~ logicFactor |
      logicConstant |
      LEFT_PARENTHESIS ~ logicExpression ~ RIGHT_PARENTHESIS
  }

  def logicConstant: Parser[Any] = {
    arithExpression | boolean | integer | identifier
  }

  def expression: Parser[Any] = {
    logicExpression | arithExpression
  }
}