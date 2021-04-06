package io.zana.zapl.test.translator.primitive

import io.zana.zapl.parser.base.Base
import io.zana.zapl.parser.base.Base.{Error, Failure, Success}
import io.zana.zapl.{parser, standard, structure, translator}

trait Base {

  def parse(s: java.lang.String): structure.primitive.Primitive = {
    Base.parse(parser.primitive.Primitive.apply, s) match {
      case Success(s, _) => s
      case Failure(s, _) => standard.Error(standard.Error.UnknownSyntax(s))
      case Error(s, _) => standard.Error(standard.Error.UnknownSyntax(s))
    }
  }

  def translate(s: structure.primitive.Primitive): java.lang.String = {
    translator.primitive.Primitive(s)
  }

}

