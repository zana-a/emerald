package io.zana.zapl.parser.function

import io.zana.zapl.structure.function.FunctionBody
import io.zana.zapl.{parser, structure}

object Function {

  import parser.Base._
  import Keyword._
  import parser.block.Block._
  import parser.call.Call._
  import parser.primitive.Primitive._
  import structure.function.{Function => Result}

  def function: Parser[Result] = {
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

    val `return` = COLON ~> staticType

    (id ~ params ~ `return` ~ body) ^^ {
      case id ~ params ~ r ~ body => Result(id, params, r, body)
    }
  }
}
