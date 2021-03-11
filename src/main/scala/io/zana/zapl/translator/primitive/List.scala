package io.zana.zapl.translator.primitive

import io.zana.zapl.structure.primitive
import io.zana.zapl.translator
import io.zana.zapl.translator.Translatable

object List extends Translatable[primitive.List] {

  private def helper(list: primitive.List, result: String = ""): List[Any] = {
    for {
      item <- list.value
    } yield item match {
      case primitive.String(_) =>
        result ++ translator
          .primitive
          .String
          .apply(item.asInstanceOf[primitive.String])

      case primitive.Integer(_) =>
        result ++ translator
          .primitive
          .Integer
          .apply(item.asInstanceOf[primitive.Integer])

      case primitive.Boolean(_) =>
        result ++ translator
          .primitive
          .Boolean
          .apply(item.asInstanceOf[primitive.Boolean])

      case primitive.List(_) =>
        helper(item.asInstanceOf[primitive.List], result) ++ result
    }
  }

  override def apply(structure: primitive.List): String = {
    helper(structure).toString
  }
}
