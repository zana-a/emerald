package io.zana.zapl.test.parser.comment

trait Base {

  object Tools {

    object LineComment {
      val parser = io.zana.zapl.parser.comment.LineComment.apply
      val structure = io.zana.zapl.structure.comment.LineComment
    }

  }

}
