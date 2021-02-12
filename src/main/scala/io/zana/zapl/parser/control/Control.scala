package io.zana.zapl.parser.control

import io.zana.zapl.parser.base.Base._

object Control {

  def control: Parser[Any] =
    Cond.apply | Loop.apply
}
