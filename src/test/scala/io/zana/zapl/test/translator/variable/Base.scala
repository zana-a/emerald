package io.zana.zapl.test.translator.variable

trait Base {

  object Variable {
    def parse(input: String): io.zana.zapl.structure.variable.Variable =
      io.zana.zapl.parser.base.Base.parse(
        io.zana.zapl.parser.variable.Variable.apply,
        input
      ) match {
        case io.zana.zapl.parser.base.Base.Success(s, _) => s
        case io.zana.zapl.parser.base.Base.Failure(s, _) =>
          throw new RuntimeException(s)
        case io.zana.zapl.parser.base.Base.Error(s, _) =>
          throw new RuntimeException(s)
      }

    def translate(s: io.zana.zapl.structure.variable.Variable): String =
      io.zana.zapl.translator.variable.Variable(s)
  }


  object Assign {
    def parse(input: String): io.zana.zapl.structure.variable.Assign =
      io.zana.zapl.parser.base.Base.parse(
        io.zana.zapl.parser.variable.Assign.apply,
        input
      ) match {
        case io.zana.zapl.parser.base.Base.Success(s, _) => s
        case io.zana.zapl.parser.base.Base.Failure(s, _) =>
          throw new RuntimeException(s)
        case io.zana.zapl.parser.base.Base.Error(s, _) =>
          throw new RuntimeException(s)
      }

    def translate(s: io.zana.zapl.structure.variable.Assign): String =
      io.zana.zapl.translator.variable.Assign(s)

  }

}
