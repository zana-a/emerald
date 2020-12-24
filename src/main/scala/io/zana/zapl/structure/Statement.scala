package io.zana.zapl.structure

import io.zana.zapl.structure.Auxillery._
import io.zana.zapl.structure.Expression._

object Statement {

  sealed trait Statement

  case class Function(name: Identifier, parameters: List[Identifier], body: Nothing) extends Statement

  case class Module(name: Identifier, functionsOrModules: List[Statement]) extends Statement

  case class Variable(name: Identifier, body: Nothing)

  case class Block(statements: List[Variable | Function | Expression | Block])

  object Control {

    case class Guard(expression: Expression)

    case class Command(body: Expression | Statement)

    case class GuardCommand(guard: Guard, command: Command)

    // -------------------------

    case class If(guardCommands: List[GuardCommand]) extends Statement

    case class While() extends Statement

  }

}
