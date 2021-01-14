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
		arithExpression | boolean | integer | identifier
	}

	def expression: Parser[Any] = {
		logicExpression | arithExpression
	}
}