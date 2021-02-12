package io.zana.zapl.parser.statics

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.statics.{Static => Structure}

object Static {

  def int: Parser[Structure.Int.type] =
    STATIC_T_INT ^^ (_ => Structure.Int)

  def string: Parser[Structure.String.type] =
    STATIC_T_STRING ^^ (_ => Structure.String)

  def boolean: Parser[Structure.Boolean.type] =
    STATIC_T_BOOLEAN ^^ (_ => Structure.Boolean)

  def list: Parser[Structure.List] = {
    STATIC_T_LIST ~> (LEFT_ANGLE ~> static <~ RIGHT_ANGLE) ^^
      (generic => Structure.List(generic))
  }

  def static: Parser[Structure.Static] = {
    int | string | boolean | list
  }

}
