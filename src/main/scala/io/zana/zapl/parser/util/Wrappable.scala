package io.zana.zapl.parser.util

import io.zana.zapl.parser.base.Base.Parser

trait Wrappable[T] {
  def apply(parser: T): Parser[T]
}
