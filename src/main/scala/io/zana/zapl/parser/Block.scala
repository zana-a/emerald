package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Call._
import io.zana.zapl.parser.Control._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Function._
import io.zana.zapl.parser.Keyword._
import io.zana.zapl.structure

object Block {

	def block: Parser[Any] = {
		DO ~> opt(rep(expression | control | call)) <~ END ^^ {
			case Some(values) => structure.Block(values)
			case None => structure.Block(List())
		}
	}
}
