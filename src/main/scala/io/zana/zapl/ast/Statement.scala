package io.zana.zapl.ast

import io.zana.zapl.ast.Expression.{Definition, Expressor}

object Statement {

  trait Statement

  case class Append(statements: scala.List[Statement]) extends Statement

  case object Skip extends Statement

  case class Guard(expressor: Expressor, statement: Statement)

  case class If(guardedCommands: scala.List[Guard]) extends Statement

  case class Do(guardedCommands: scala.List[Guard]) extends Statement

  case class Block(definitions: scala.List[Definition], statements: scala.List[Statement])
    extends Statement

}