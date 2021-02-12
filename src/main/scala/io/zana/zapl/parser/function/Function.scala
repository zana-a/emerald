package io.zana.zapl.parser.function

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.block.Block._
import io.zana.zapl.parser.call.Call._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.parser.primitive.Primitive._
import io.zana.zapl.parser.statics.Static._
import io.zana.zapl.structure.function.{FunctionBody, Function => Structure}

object Function {


  def function: Parser[Structure] = {
    val id = DEF ~> identifier

    val params = LEFT_PAREN ~> repsep(Parameter.parameter, COMMA) <~ RIGHT_PAREN

    val body: Parser[FunctionBody] = EQ ~> (
      `type`
        | call
        | identifier
        //todo        | expression
        | block
      // todo       | control
      )

    val `return` = COLON ~> static

    (id ~ params ~ `return` ~ body) ^^ {
      case id ~ params ~ r ~ body => Structure(id, params, r, body)
    }
  }
}
