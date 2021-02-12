package io.zana.zapl.translator.function

import io.zana.zapl.parser.keyword.Keyword._
import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.call.{Function, Module}
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

object Function extends Translatable[structures.function.Function] {

  override def translate(structure: structures.function.Function): String = {
    val name = translator.common.Identifier.translate(structure.name)

    val param = for {
      param <- structure.params
    } yield s"${
      translator
        .common
        .Identifier
        .translate(param.name)
    }: ${translator.statics.Static.translate(param.static)}"

    val `return` = translator.statics.Static.translate(structure.`return`)

    val body: String = structure.body match {
      case primitive: Primitive =>
        translator.primitive.Primitive.translate(primitive)

      case functionCall: Function =>
        translator.call.FunctionCall.translate(functionCall)

      case moduleCall: Module =>
        translator.call.ModuleCall.translate(moduleCall)

      case block: Block =>
        translator.block.Block.translate(block)

      case identifier: Identifier =>
        translator.common.Identifier.translate(identifier)

      case e => s"??? not implemented for $e"
    }

    s"def $name(${param.mkString(COMMA ++ " ")}): ${`return`} = $body"
  }
}
