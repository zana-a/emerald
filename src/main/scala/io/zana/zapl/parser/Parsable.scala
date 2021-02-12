package io.zana.zapl.parser

import io.zana.zapl.parser.base.Base._

trait Parsable {

  type T

  def parse(): Parser[T]
}
