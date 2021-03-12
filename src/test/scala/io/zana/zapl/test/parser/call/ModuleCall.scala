package io.zana.zapl.test.parser.call

import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class ModuleCall extends Base {

  @Test
  def one(): Unit = {
    Tester(
      Tools.ModuleCall.parser,
      "A::a()",
      Tools.ModuleCall.structure(
        List(
          Identifier("A"),
        ),
        Tools.FunctionCall.structure(
          Identifier("a"),
          List()
        )
      )
    )
  }

  @Test
  def many(): Unit = {
    Tester(
      Tools.ModuleCall.parser,
      "A::B::C::D::a()",
      Tools.ModuleCall.structure(
        List(
          Identifier("A"),
          Identifier("B"),
          Identifier("C"),
          Identifier("D"),
        ),
        Tools.FunctionCall.structure(
          Identifier("a"),
          List()
        )
      )
    )
  }
}
