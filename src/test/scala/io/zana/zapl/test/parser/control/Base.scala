package io.zana.zapl.test.parser.control

trait Base {

  object Tools {

    object Cond {
      val parser = io.zana.zapl.parser.control.Cond.apply
    }

    object Loop {
      val parser = io.zana.zapl.parser.control.Loop.apply
    }

  }

  val parser = io.zana.zapl.parser.control.Control.apply

}
