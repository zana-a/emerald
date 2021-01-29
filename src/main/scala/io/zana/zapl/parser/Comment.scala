package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.comment

object Comment {

  def singleLine: Parser[comment.LineComment] =
    """#.*""".r ^^ {
      case c => comment.LineComment(c.drop(1))
    }
}
