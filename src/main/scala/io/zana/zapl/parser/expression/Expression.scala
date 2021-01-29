package io.zana.zapl.parser.expression

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.primitive.Primitive._

object Expression {

  def arithExpression: Parser[Any] = {
    arithFactor ~ opt(rep(
      Keyword.PLUS ~ arithExpression
        | Keyword.MINUS ~ arithExpression
        | Keyword.MULTIPLICATION ~ arithExpression
        | Keyword.DIVISION ~ arithExpression))
  }

  def arithFactor: Parser[Any] =
    arithConstant | Keyword.LEFT_PARENTHESIS ~ arithExpression ~ Keyword.RIGHT_PARENTHESIS

  def arithConstant: Parser[Any] = integer | identifier

  def logicExpression: Parser[Any] =
    logicFactor ~ opt(rep(
      Keyword.AND ~ logicExpression
        | Keyword.OR ~ logicExpression
        | Keyword.EQEQ ~ logicExpression
        | Keyword.NEQ ~ logicExpression
        | Keyword.LT ~ logicExpression
        | Keyword.GT ~ logicExpression
        | Keyword.LTEQ ~ logicExpression
        | Keyword.GTEQ ~ logicExpression
    ))

  def logicFactor: Parser[Any] =
    Keyword.NOT ~ logicFactor |
      logicConstant |
      Keyword.LEFT_PARENTHESIS ~ logicExpression ~ Keyword.RIGHT_PARENTHESIS

  def logicConstant: Parser[Any] =
    arithExpression | boolean | integer | identifier

  def expression: Parser[Any] =
    logicExpression | arithExpression ^^ {
      case expression => expression
    }
}
