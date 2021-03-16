package io.zana.zapl.test.parser.function

import io.zana.zapl.structure.statics
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Parameter extends Base {

  @Test
  def boolean(): Unit = {
    Tester(
      Tools.Parameter.parse,
      "a: Boolean",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.Boolean
      )
    )
  }

  @Test
  def string(): Unit = {
    Tester(
      Tools.Parameter.parse,
      "a: String",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.String
      )
    )
  }

  @Test
  def list(): Unit = {
    Tester(
      Tools.Parameter.parse,
      "a: Boolean",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.Boolean
      )
    )
    Tester(
      Tools.Parameter.parse,
      "a: String",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.String
      )
    )
    Tester(
      Tools.Parameter.parse,
      "a: Integer",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.Integer
      )
    )
    Tester(
      Tools.Parameter.parse,
      "a: List<Any>",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.List(statics.Any)
      )
    )
    Tester(
      Tools.Parameter.parse,
      "a: Any",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.Any
      )
    )
  }


  @Test
  def any(): Unit = {
    Tester(
      Tools.Parameter.parse,
      "a: Any",
      Tools.Parameter.structure(
        Identifier("a"),
        statics.Any
      )
    )
  }

}
