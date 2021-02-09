package io.zana.zapl.parser

import io.zana.zapl.structure
import io.zana.zapl.structure.common.StaticTypes

import scala.util.parsing.combinator._

object Base extends JavaTokenParsers {

  import Keyword._
  import structure.common.Identifier

  def staticType: Parser[StaticTypes.Static] = {
    def int: Parser[StaticTypes.Int.type] =
      STATIC_T_INT ^^ (_ => StaticTypes.Int)

    def string: Parser[StaticTypes.String.type] =
      STATIC_T_STRING ^^ (_ => StaticTypes.String)

    def boolean: Parser[StaticTypes.Boolean.type] =
      STATIC_T_BOOLEAN ^^ (_ => StaticTypes.Boolean)

    def list: Parser[StaticTypes.List.type] =
      STATIC_T_LIST ^^ (_ => StaticTypes.List)

    int | string | boolean | list
  }

  object Keyword {

    val TRUE = "true"

    val FALSE = "false"

    val DEF = "def"

    val DO = "do"

    val END = "end"

    val COND = "cond"

    val LOOP = "loop"

    val MOD = "mod"

    val STATIC_T_INT = "Int"

    val STATIC_T_STRING = "String"

    val STATIC_T_LIST = "List"

    val STATIC_T_BOOLEAN = "Boolean"

    val LEFT_PAREN = "("

    val RIGHT_PAREN = ")"

    val LEFT_BRACKET = "["

    val RIGHT_BRACKET = "]"

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

  def identifier: Parser[Identifier] =
    not(Keyword.nonSymbol) ~> super.ident ^^ (id => Identifier(id))
}
