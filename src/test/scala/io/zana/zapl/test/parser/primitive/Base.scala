package io.zana.zapl.test.parser.primitive

import junit.framework.TestSuite

trait Base extends TestSuite {

  object Tools {

    val parser = io.zana.zapl.parser.primitive.Primitive.apply
  }

}
