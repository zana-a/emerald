package io.zana.zapl.test.translator.expression

trait Base {

  def parse(input: String): io.zana.zapl.structure.expression.Expression =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.expression.Expression.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.expression.Expression): String =
    io.zana.zapl.translator.expression.Expression(s)

}
