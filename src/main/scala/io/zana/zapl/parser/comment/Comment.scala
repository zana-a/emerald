package io.zana.zapl.parser.comment

import io.zana.zapl.{parser, structure}

object Comment {

  import parser.Base._
  import structure.comment.{LineComment => Result}

  def lineComment: Parser[Result] =
    """#.*""".r ^^ (c => Result(c.drop(1)))
}
