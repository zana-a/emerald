package io.zana.zapl.parser.expression.predef

import io.zana.zapl.structure.call.CallBody
import io.zana.zapl.structure.identifier.Identifier

trait Expr

case class Let(v: Variable, e1: Expr, e2: Expr) extends Expr

case class Literal(x: Int) extends Expr

case class Variable(name: String) extends Expr

case class Single(sym: String, e: Expr) extends Expr

case class Pair(sym: String, e1: Expr, e2: Expr) extends Expr

trait Call extends Expr

case class FunctionCall(name: Identifier, params: List[CallBody]) extends Call

case class ModuleCall(modules: List[Identifier], caller: FunctionCall)
  extends Call
