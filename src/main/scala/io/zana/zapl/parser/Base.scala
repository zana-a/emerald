package io.zana.zapl.parser

import io.zana.zapl.structure

import scala.util.parsing.combinator._

object Base extends JavaTokenParsers {

  import structure.common.Identifier

  object Keyword {

    val TRUE = "true"

    val FALSE = "false"

    val DEF = "def"

    val DO = "do"

    val END = "end"

    val IF = "if"

    val WHILE = "while"

    val MOD = "mod"

    val LEFT_PAREN = "("

    val RIGHT_PAREN = ")"

    val LEFT_BRACKET = "["

    val RIGHT_BRACKET = "]"

    val DQUOTE = "\""

    val UNDERSCORE = "_"

    val FAT_ARROW = "=>"

    val COMMA = ","

    val PLUS = "+"

    val MULTIPLICATION = "*"

    val MINUS = "-"

    val DIVISION = "/"

    val AND = "&&"

    val OR = "||"

    val EQEQ = "=="

    val NEQ = "!="

    val LT = "<"

    val GT = ">"

    val LTEQ = "<="

    val GTEQ = ">="

    val NOT = "!"

    val BOX = "::"

    val EQ = "="

    def nonSymbol: Parser[String] =
      TRUE | FALSE | DEF | DO | END | IF | WHILE | MOD
  }

  def identifier: Parser[Identifier] =
    not(Keyword.nonSymbol) ~> super.ident ^^ (id => Identifier(id))
}
