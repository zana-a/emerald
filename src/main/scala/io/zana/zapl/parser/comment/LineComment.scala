package io.zana.zapl.parser.comment

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.util.Parsable
import io.zana.zapl.structure.comment.{LineComment => Structure}

object LineComment extends Parsable[Structure] {

  override def apply: Parser[Structure] =
    """#.*""".r ^^ (c => Structure(c.drop(1)))
}
