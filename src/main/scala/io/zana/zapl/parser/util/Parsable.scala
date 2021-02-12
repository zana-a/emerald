package io.zana.zapl.parser.util

import io.zana.zapl.parser.base.Base.Parser

trait Parsable[T] {

  def apply: Parser[T]
}
