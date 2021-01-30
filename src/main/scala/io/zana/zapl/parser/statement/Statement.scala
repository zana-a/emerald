package io.zana.zapl.parser.statement

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.function.Function._
import io.zana.zapl.parser.module.Module._

object Statement {

  def statement: Parser[Any] = {
    module | function
  }
}
