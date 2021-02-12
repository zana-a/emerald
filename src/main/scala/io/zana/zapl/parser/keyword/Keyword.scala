package io.zana.zapl.parser.keyword

import io.zana.zapl.parser.base.Base._

object Keyword {

  val TRUE = "true"

  val FALSE = "false"

  val DEF = "def"

  val DO = "do"

  val END = "end"

  val COND = "cond"

  val LOOP = "loop"

  val MOD = "mod"

  val LET = "let"

  val MUT = "mut"

  val STATIC_T_INT = "Int"

  val STATIC_T_STRING = "String"

  val STATIC_T_LIST = "List"

  val STATIC_T_BOOLEAN = "Boolean"

  val STATIC_T_ANY = "Any"

  val LEFT_PAREN = "("

  val RIGHT_PAREN = ")"

  val LEFT_BRACKET = "["

  val RIGHT_BRACKET = "]"

  val LEFT_ANGLE = "<"

  val RIGHT_ANGLE = ">"

  val DQUOTE = "\""

  val UNDERSCORE = "_"

  val FAT_ARROW = "=>"

  val THIN_ARROW = "->"

  val COMMA = ","

  val COLON = ":"

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
    TRUE | FALSE | DEF | DO | END | COND | LOOP | MOD
}
