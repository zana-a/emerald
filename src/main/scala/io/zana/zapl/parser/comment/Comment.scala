package io.zana.zapl.parser.comment

import io.zana.zapl.parser.Base._
import io.zana.zapl.structure.comment.{LineComment => Structure}

object Comment {

  def lineComment: Parser[Structure] =
    """#.*""".r ^^ (c => Structure(c.drop(1)))
}
