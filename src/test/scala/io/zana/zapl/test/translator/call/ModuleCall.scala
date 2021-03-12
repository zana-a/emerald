package io.zana.zapl.test.translator.call

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class ModuleCall extends Base {

  @Test
  def simple(): Unit = {
    Tester(
      "A.a()",
      translate(
        parse(
          "A::a()"
        )
      )
    )
  }

  @Test
  def multipleModule(): Unit = {
    Tester(
      "A.B.C.a()",
      translate(
        parse(
          "A::B::C::a()"
        )
      )
    )
  }
}
