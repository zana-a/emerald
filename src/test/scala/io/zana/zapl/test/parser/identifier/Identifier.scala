package io.zana.zapl.test.parser.identifier

import io.zana.zapl.test.parser.Tester
import junit.framework.TestSuite
import org.junit.Test

class Identifier extends TestSuite {

  val parser = io.zana.zapl.parser.identifier.Identifier.apply
  val structure = io.zana.zapl.structure.identifier.Identifier

  @Test
  def simple(): Unit = {
    Tester(
      parser,
      "a",
      structure("a")
    )
    Tester(
      parser,
      "abc",
      structure("abc")
    )
    Tester(
      parser,
      "a123",
      structure("a123")
    )
    Tester(
      parser,
      "a_b",
      structure("a_b")
    )
    Tester(
      parser,
      "a_b_",
      structure("a_b_")
    )
    Tester(
      parser,
      "Abc",
      structure("Abc")
    )
  }
}
