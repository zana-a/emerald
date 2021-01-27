package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Block._
import io.zana.zapl.parser.Call._
import io.zana.zapl.parser.Control._
import io.zana.zapl.parser.Expression._
import io.zana.zapl.parser.Operator._
import io.zana.zapl.parser.Primitive._

object Variable {

  def variable: Parser[Any] =
    identifier ~ EQ ~ (`type` | expression | control | call | block)
}
