package io.zana.zapl.test.parser.variable

trait Base {

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
