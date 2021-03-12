package io.zana.zapl.test.translator.call

trait Base {

  def parse(input: String): io.zana.zapl.structure.call.Callable =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.call.Call.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.call.Callable): String =
    io.zana.zapl.translator.call.Callable(s)

}

