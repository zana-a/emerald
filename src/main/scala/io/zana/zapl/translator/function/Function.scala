package io.zana.zapl.translator.function

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.call.{
  Function => FunctionCall,
  Module => ModuleCall
}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Function extends Translatable[structures.function.Function] {

  override def apply(structure: structures.function.Function): String = {
    val name = translator.common.Identifier.apply(structure.name)

    val param = for {
      param <- structure.params
    } yield s"${
      translator
        .common
        .Identifier
        .apply(param.name)
    }: ${translator.statics.Static.apply(param.static)}"

    val `return` = translator.statics.Static.apply(structure.static)

    val body: String = structure.body match {
      case primitive: Primitive =>
        translator.primitive.Primitive.apply(primitive)

      case functionCall: FunctionCall =>
        translator.call.FunctionCall.apply(functionCall)

      case moduleCall: ModuleCall =>
        translator.call.ModuleCall.apply(moduleCall)

      case block: Block =>
        translator.block.Block.apply(block)

      case identifier: Identifier =>
        translator.common.Identifier.apply(identifier)

      case e => s"??? not implemented for $e"
    }

    s"def $name(${param.mkString(COMMA ++ " ")}): ${`return`} = $body"
  }
}
