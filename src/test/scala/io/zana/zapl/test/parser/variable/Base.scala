package io.zana.zapl.test.parser.variable

import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.structure.{primitive, statics}
import io.zana.zapl.test.parser.Tester
import junit.framework.TestSuite
import org.junit.Test

trait Base extends TestSuite {

  object Tools {

    object Variable {
      val parser = io.zana.zapl.parser.variable.Variable.apply
      val structure = io.zana.zapl.structure.variable.Variable
    }

    object Assign {
      val parser = io.zana.zapl.parser.variable.Assign.apply
      val structure = io.zana.zapl.structure.variable.Assign
    }

  }

}
