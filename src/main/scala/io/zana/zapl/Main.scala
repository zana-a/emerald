package io.zana.zapl

object Main extends App {

  //  val parse = parser.base.Base.parse(
  //    parser.program.Program.apply,
  //    {
  //      """
  //        |let a: String = 2
  //        |let mut b: Int = 2
  //        |
  //        |def add(x: Int, y: Int): Int = x
  //        |
  //        |def some_string(): String = do
  //        |  let mut some_mutable_string: String = "some string"
  //        |  some_mutable_string
  //        |end
  //        |
  //        |mod A do
  //        |
  //        |  mod B do
  //        |    def add(x: Int, y: Int): Int = x
  //        |  end
  //        |
  //        |  def some_string(): String = do
  //        |    let mut some_mutable_string: String = "some string"
  //        |    some_mutable_string
  //        |  end
  //        |end
  //        |
  //        |add(1, 2)
  //        |A::B(3, 4)
  //        |"""
  //        .stripMargin
  //    }
  //  )
  //
  //  parse match {
  //    case parser.base.Base.Success(s, _) =>
  //      pprint.pprintln(s, height = 9000, width = 2)
  //
  //    case parser.base.Base.NoSuccess(s, _) =>
  //      throw new RuntimeException(s)
  //  }

  val parse = parser.base.Base.parse(
    parser.control.Control.apply,
    {
      """
        |cond do
        |  true => true
        |  _ => false
        |end
        |"""
        .stripMargin
    }
  )

  parse match {
    case parser.base.Base.Success(s, _) =>
      pprint.pprintln(s, height = 9000, width = 2)

    case parser.base.Base.NoSuccess(s, _) =>
      throw new RuntimeException(s)
  }
}
