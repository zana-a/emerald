package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object List extends Translatable[primitive.List] {

  private def helper(list: primitive.List, result: String = ""): List[String] =
    for {item <- list.value} yield item match {
      case e: primitive.String => result + translator.primitive.String.apply(e)
      case e: primitive.Integer => result + translator.primitive.Integer.apply(e)
      case e: primitive.Boolean => result + translator.primitive.Boolean.apply(e)
      case e: primitive.List => helper(e, result) + result
    }

  override def apply(structure: primitive.List): String = {
    helper(structure).toString
  }
}
