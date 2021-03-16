package io.zana.zapl.structure.expression

import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.call.CallBody
import io.zana.zapl.structure.function.FunctionBody
import io.zana.zapl.structure.identifier.Identifier

trait Expression extends FunctionBody with BlockBody with CallBody

case class Variable(name: String) extends Expression

case class Single(sym: String, e: Expression) extends Expression

case class Pair(sym: String, e1: Expression, e2: Expression) extends Expression

trait Call extends Expression

//case class FunctionCall(name: Identifier, params: List[CallBody]) extends Call
//
//case class ModuleCall(modules: List[Identifier], caller: FunctionCall)
//  extends Call
