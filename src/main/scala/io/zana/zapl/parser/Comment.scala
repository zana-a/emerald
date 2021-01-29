package io.zana.zapl.parser

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.comment

object Comment {

  def singleLineComment: Parser[comment.LineComment] =
    """#.*""".r ^^ {
      case c => comment.LineComment(c.drop(1))
    }
}
