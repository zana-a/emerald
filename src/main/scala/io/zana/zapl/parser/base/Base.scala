package io.zana.zapl.parser.base

import scala.util.parsing.combinator.JavaTokenParsers

object Base extends JavaTokenParsers {
  override def ident: Base.Parser[String] = """([a-zA-Z]|[a-zA-Z]){1}[a-zA-Z0-9_]*""".r
}