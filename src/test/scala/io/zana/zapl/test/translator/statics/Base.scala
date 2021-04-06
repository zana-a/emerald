package io.zana.zapl.test.translator.statics

trait Base {

  def parse(input: String): io.zana.zapl.structure.statics.Static =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.statics.Static.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.statics.Static): String =
    io.zana.zapl.translator.statics.Static(s)

}
