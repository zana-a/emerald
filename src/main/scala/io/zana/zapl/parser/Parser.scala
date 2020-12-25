package io.zana.zapl
package parser

import io.zana.zapl.structure.Type

import scala.util.parsing.combinator.JavaTokenParsers

object Parser extends JavaTokenParsers {

  def identifier: Parser[String] = {
    import Keyword.keywords
    not(keywords) ~> super.ident
  }

  object Block {

    import Call._
    import Control._
    import Expression._
    import Function._
    import Keyword._

    def block: Parser[Any] =
      DO ~ (expression | function | control | call) ~ END
  }

  object Control {

    import Block._
    import Expression._
    import Keyword._
    import Operator._
    import Primitive._

    def guard: Parser[Any] = logicExpression

    def command: Parser[Any] = identifier | `type` | expression | block

    def condition: Parser[Any] = guard ~ FAT_ARROW ~ command

    def defaultCondition: Parser[Any] = UNDERSCORE ~ FAT_ARROW ~ command

    def `if`: Parser[Any] = IF ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

    def `while`: Parser[Any] = WHILE ~ DO ~ opt(rep(condition)) ~ defaultCondition ~ END

    def control: Parser[Any] = `if` | `while`
  }

  object Expression {

    import Operator._
    import Primitive._

    def arithExpression: Parser[Any] = {
      arithFactor ~ opt(rep(
        PLUS ~ arithExpression
          | MINUS ~ arithExpression
          | MULTIPLICATION ~ arithExpression
          | DIVISION ~ arithExpression))
    }


    def arithFactor: Parser[Any] = {
      arithConstant | LEFT_PARENTHESIS ~ arithExpression ~ RIGHT_PARENTHESIS
    }

    def arithConstant: Parser[Any] = integer | identifier

    def logicExpression: Parser[Any] = {
      logic_factor ~ opt(rep(
        AND ~ logicExpression
          | OR ~ logicExpression
          | EQEQ ~ logicExpression
          | NEQ ~ logicExpression
          | LT ~ logicExpression
          | GT ~ logicExpression
          | LTEQ ~ logicExpression
          | GTEQ ~ logicExpression
      ))
    }

    def logic_factor: Parser[Any] = {
      NOT ~ logic_factor |
        logic_constant |
        LEFT_PARENTHESIS ~ logicExpression ~ RIGHT_PARENTHESIS
    }

    def logic_constant: Parser[Any] = {
      arithExpression | boolean | integer | identifier
    }

    def expression: Parser[Any] = {
      logicExpression | arithExpression
    }
  }

  object Function {

    import Block._
    import Call._
    import Control._
    import Expression._
    import Keyword._
    import Operator._
    import Primitive._

    def function: Parser[Any] = {
      DEF ~
        identifier ~ LEFT_PARENTHESIS ~ opt(rep(identifier)) ~ RIGHT_PARENTHESIS ~
        EQ ~ (identifier | `type` | call | control | expression | block)
    }
  }

  object Keyword {
    val TRUE = "true"

    val FALSE = "false"

    val DEF = "def"

    val DO = "do"

    val END = "end"

    val IF = "if"

    val WHILE = "WHILE"

    val MOD = "MOD"

    def keywords: Parser[String] =
      TRUE | FALSE | DEF | DO | END | IF | WHILE | MOD
  }

  object Module {

    import Function._
    import Keyword._

    def moduleIdentifier: Parser[String] =
      """^([A-Z])(\w?)+(\d?)+""".r

    def module: Parser[Any] =
      MOD ~ moduleIdentifier ~ DO ~ opt(rep(function)) ~ END
  }

  object Operator {
    val LEFT_PARENTHESIS = "("

    val RIGHT_PARENTHESIS = ")"

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
  }

  object Primitive {

    import Keyword._

    def boolean: Parser[Type.Boolean] = {
      def t: Parser[Type.Boolean] = TRUE ^^ {
        result => Type.Boolean(result.toBoolean)
      }

      def f: Parser[Type.Boolean] = FALSE ^^ {
        result => Type.Boolean(result.toBoolean)
      }

      t | f ^^ (result => result)
    }

    def string: Parser[Type.String] = stringLiteral ^^ {
      result => Type.String(result)
    }

    def list: Parser[Type.List] = "[" ~> repsep(`type`, ",") <~ "]" ^^ {
      result => Type.List(result)
    }

    def integer: Parser[Type.Integer] = wholeNumber ^^ {
      result => Type.Integer(result.toInt)
    }

    def `type`: Parser[Type.Type[_]] = string | integer | list | boolean
  }

  object Call {

    import Module._
    import Operator._
    import Primitive._

    def call: Parser[Any] = moduleFnCall | fnCall

    def fnCallParams: Parser[Any] = opt(rep(`type` | identifier | fnCall | moduleFnCall))

    def fnCall: Parser[Any] = identifier ~ LEFT_PARENTHESIS ~ fnCallParams ~ RIGHT_PARENTHESIS

    def moduleFnCallIdentifier: Parser[Any] = moduleIdentifier ~ opt(rep(BOX ~ moduleIdentifier))

    def moduleFnCall: Parser[Any] = moduleFnCallIdentifier ~ BOX ~ fnCall

  }

  object Variable {

    import Block._
    import Call._
    import Control._
    import Expression._
    import Operator._
    import Primitive._

    def variable: Parser[Any] =
      identifier ~ EQ ~ (`type` | expression | control | call | block)
  }

  object Statement {

    import Function._
    import Module._

    def statement: Parser[Any] = module | function
  }

  object Program {

    import Call._
    import Control._
    import Expression._
    import Statement._

    def build: Parser[Any] = opt(rep(statement | expression | control | call))
  }

}
