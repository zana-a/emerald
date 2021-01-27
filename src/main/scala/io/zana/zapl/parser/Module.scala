package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Function._
import io.zana.zapl.parser.Keyword._

object Module {

  def module: Parser[Any] =
    MOD ~ identifier ~ DO ~ opt(rep(function)) ~ END
}
