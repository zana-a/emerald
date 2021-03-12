package io.zana.zapl.test.translator.comment

trait Base {

  object Tools {

    object LineComment {

      def parse(input: String): io.zana.zapl.structure.comment.LineComment = {
        io.zana.zapl.parser.base.Base.parse(
          io.zana.zapl.parser.comment.LineComment.apply,
          input
        ) match {
          case io.zana.zapl.parser.base.Base.Success(s, _) => s
          case io.zana.zapl.parser.base.Base.Failure(s, _) =>
            throw new RuntimeException(s)
          case io.zana.zapl.parser.base.Base.Error(s, _) =>
            throw new RuntimeException(s)
        }
      }

      def translate(lc: io.zana.zapl.structure.comment.LineComment): String =
        io.zana.zapl.translator.comment.LineComment(lc)
    }

  }

}
