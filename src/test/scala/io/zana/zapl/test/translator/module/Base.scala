package io.zana.zapl.test.translator.module

trait Base {

  def parse(input: String): io.zana.zapl.structure.module.Module =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.module.Module.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.module.Module): String =
    io.zana.zapl.translator.module.Module(s)


}
