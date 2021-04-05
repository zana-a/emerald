package io.zana.zapl.test.translator.function

trait Base {

  def parse(input: String): io.zana.zapl.structure.function.Function =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.function.Function.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.function.Function): String =
    io.zana.zapl.translator.function.Function(s)


}
