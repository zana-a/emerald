package io.zana.zapl.ast

import io.zana.zapl.ast.{Expression, Type}

object Statement {

  trait Statement

  case class Append(statements: scala.List[Statement]) extends Statement

  case class Guard(expressor: Expression.Expression, statement: Statement)

  case class If(guardedCommands: scala.List[Guard]) extends Statement

  case class Loop(guardedCommands: scala.List[Guard]) extends Statement

  case class Block(statements: scala.List[Statement]) extends Statement

  case class Definition(identifier: java.lang.String, value: Type.Type) extends Statement

  case class Function(name: String, params: List[String], block: Block) extends Statement

  case class Module(name: String, functions: List[Function]) extends Statement

  case object Skip extends Statement

}

