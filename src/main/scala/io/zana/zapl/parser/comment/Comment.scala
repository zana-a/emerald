package io.zana.zapl.parser.comment

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.comment.{LineComment => Result}

object Comment {

  def singleLineComment: Parser[Result] =
    """#.*""".r ^^ (c => Result(c.drop(1)))
}
