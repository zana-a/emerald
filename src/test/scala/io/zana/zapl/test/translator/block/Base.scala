package io.zana.zapl.test.translator.block

import io.zana.zapl.parser.base.Base
import io.zana.zapl.parser.base.Base.{Success, Failure, Error}
import io.zana.zapl.parser
import io.zana.zapl.structure
import io.zana.zapl.standard
import io.zana.zapl.translator

trait Base {

  def parse(s: String): structure.block.Block = {
    Base.parse(parser.block.Block.apply, s) match {
      case Success(s, _) => s
      case Failure(s, _) => standard.Error(standard.Error.UnknownSyntax(s))
      case Error(s, _) => standard.Error(standard.Error.UnknownSyntax(s))
    }
  }

  def translate(s: structure.block.Block): java.lang.String = {
    translator.block.Block(s)
  }
}
