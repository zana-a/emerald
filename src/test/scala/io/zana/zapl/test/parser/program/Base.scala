package io.zana.zapl.test.parser.program

trait Base {

  object Tools {
    val parser = io.zana.zapl.parser.program.Program.apply
    val structure = io.zana.zapl.structure.program.Program
  }

}
