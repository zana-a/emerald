package io.zana.zapl.parser

import io.zana.zapl.ast.Expression.Definition
import io.zana.zapl.ast.{Expression, Type}

import scala.util.parsing.combinator._

object Parser extends JavaTokenParsers {
  def lowerAlpha: Parser[String] = "[a-z]".r

  def upperAlpha: Parser[String] = "[A-Z]".r

  def alpha: Parser[String] = lowerAlpha | upperAlpha

  def zero: Parser[String] = "0"

  def numeral: Parser[String] = "[1-9]".r

  def numeric: Parser[String] = zero | numeral

  def alphanumeric: Parser[String] = alpha | numeric

  def identifier: Parser[String] = alpha ~ rep(alphanumeric) ^^ {
    case alpha ~ alphanumeric => alpha + alphanumeric.mkString
  }

  def integer: Parser[Type.Integer] = numeral ~ opt(rep(numeric)) ^^ {
    case numeral ~ opt => opt match {
      case Some(value) => Type.Integer((numeral + value.mkString).toInt)
      case None => Type.Integer(numeral.toInt)
    }
  }

  def string: Parser[Type.String] = "\"" ~> opt(rep(alphanumeric)) <~ "\"" ^^ {
    case string => string match {
      case Some(value) => Type.String(value.mkString)
      case None => Type.String("")
    }
  }

  def `true`: Parser[Type.Bool] = "true" ^^ {
    case t => Type.Bool(t.toBoolean)
  }


  def `false`: Parser[Type.Bool] = "false" ^^ {
    case t => Type.Bool(t.toBoolean)
  }

  def bool: Parser[Type.Bool] = `true` | `false`

  def `type`: Parser[Type.Type] = string | integer | bool

  def definition: Parser[Definition] = identifier ~ ":=" ~ `type` ^^ {
    case identifier ~ _ ~ t => Expression.Definition(identifier, t)
  }
}
