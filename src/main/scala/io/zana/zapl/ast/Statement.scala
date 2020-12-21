package io.zana.zapl.ast

import io.zana.zapl.ast.{Expression, Type}

object Statement {

  trait Statement

  case class Append(statements: List[Statement]) extends Statement

  case class Guard(expressor: Expression.Expression, statement: Statement)

  case class If(guardedCommands: List[Guard]) extends Statement

  case class Loop(guardedCommands: List[Guard]) extends Statement

  case class Block(statements: List[Statement]) extends Statement

  case class ModuleBlock(functions: List[Function]) extends Statement

  case class Definition(identifier: java.lang.String, value: Type.Type) extends Statement

  case class Function(name: String, params: List[String], block: Block) extends Statement

  case class Module(name: String, functions: ModuleBlock) extends Statement

  case object Skip extends Statement

}

