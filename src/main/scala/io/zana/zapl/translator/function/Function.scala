package io.zana.zapl.translator.function

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.call.{FunctionCall, ModuleCall}
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Function extends Translatable[structures.function.Function] {

  override def apply(structure: structures.function.Function): String = {
    val name = translator.identifier.Identifier(structure.name)

    val params =
      for {param <- structure.params}
        yield s"${translator.identifier.Identifier(param.name)}:" + " " +
          s"${translator.statics.Static(param.static)}"

    val `return` = translator.statics.Static(structure.static)

    val body = structure.body match {
      case e: Primitive => translator.primitive.Primitive(e)
      case e: Identifier => translator.identifier.Identifier(e)
      case e: FunctionCall => translator.call.FunctionCall(e)
      case e: ModuleCall => translator.call.ModuleCall(e)
      case e: Block => translator.block.Block(e)
      case e: Expression =>
        translator.expression.Expression.sanitise(
          translator.expression.Expression(e)
        )
      case e => s"??? not implemented for $e"
    }

    s"def $name(${params.mkString(COMMA ++ " ")}): ${`return`} = $body"
  }
}
