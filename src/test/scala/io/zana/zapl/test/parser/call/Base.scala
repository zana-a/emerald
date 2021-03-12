package io.zana.zapl.test.parser.call

trait Base {

  object Tools {

    object FunctionCall {
      val structure = io.zana.zapl.structure.call.FunctionCall
      val parser = io.zana.zapl.parser.call.predef.FunctionCall.apply
    }

    object ModuleCall {
      val structure = io.zana.zapl.structure.call.ModuleCall
      val parser = io.zana.zapl.parser.call.predef.ModuleCall.apply
    }

  }

}
