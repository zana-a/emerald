package io.zana.zapl.structure

object Expression {

  sealed trait Expression

  case class FunctionCall() extends Expression

  case class ModuleFunctionCall() extends Expression

}
