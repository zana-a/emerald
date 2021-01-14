package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Function._
import io.zana.zapl.parser.Module._

object Statement {


  def statement: Parser[Any] = module | function
}
