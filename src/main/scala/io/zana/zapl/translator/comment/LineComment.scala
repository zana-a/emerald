package io.zana.zapl.translator.comment

import io.zana.zapl.structure.comment
import io.zana.zapl.translator.Translatable

object LineComment extends Translatable[comment.LineComment] {

  override def apply(structure: comment.LineComment): String =
    "//" + structure.comment
}
