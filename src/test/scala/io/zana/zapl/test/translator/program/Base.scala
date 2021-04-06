package io.zana.zapl.test.translator.program

trait Base {
  def parse(input: String): io.zana.zapl.structure.program.Program =
    io.zana.zapl.parser.base.Base.parse(
      io.zana.zapl.parser.program.Program.apply,
      input
    ) match {
      case io.zana.zapl.parser.base.Base.Success(s, _) => s
      case io.zana.zapl.parser.base.Base.Failure(s, _) =>
        throw new RuntimeException(s)
      case io.zana.zapl.parser.base.Base.Error(s, _) =>
        throw new RuntimeException(s)
    }

  def translate(s: io.zana.zapl.structure.program.Program): String =
    io.zana.zapl.translator.program.Program(s)

}
