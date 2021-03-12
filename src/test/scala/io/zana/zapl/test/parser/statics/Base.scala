package io.zana.zapl.test.parser.statics

trait Base {

  object Tools {

    val parser = io.zana.zapl.parser.statics.Static.apply

    val any = io.zana.zapl.structure.statics.Any
    val integer = io.zana.zapl.structure.statics.Integer
    val list = io.zana.zapl.structure.statics.List
    val string = io.zana.zapl.structure.statics.String
    val boolean = io.zana.zapl.structure.statics.Boolean
  }

}
