package io.zana.zapl.test.translator.statics

import io.zana.zapl.test.translator.Tester
import org.junit.Test

class Static extends Base {


  @Test
  def integer(): Unit = {
    Tester(
      Int.getClass.getName.dropRight(1),
      translate(
        parse(
          "Int"
        )
      )
    )
  }

  @Test
  def string(): Unit = {
    Tester(
      "java.lang.String",
      translate(
        parse(
          "String"
        )
      )
    )
  }

  @Test
  def boolean(): Unit = {
    Tester(
      Boolean.getClass.getName.dropRight(1),
      translate(
        parse(
          "Boolean"
        )
      )
    )
  }

  @Test
  def any(): Unit = {
    Tester(
      "Any",
      translate(
        parse(
          "Any"
        )
      )
    )
  }

  @Test
  def list(): Unit = {
    Tester(
      s"List[${Boolean.getClass.getName.dropRight(1)}]",
      translate(
        parse(
          "List<Boolean>"
        )
      )
    )
    Tester(
      s"List[java.lang.String]",
      translate(
        parse(
          "List<String>"
        )
      )
    )
    Tester(
      s"List[${Int.getClass.getName.dropRight(1)}]",
      translate(
        parse(
          "List<Int>"
        )
      )
    )
    Tester(
      s"List[Any]",
      translate(
        parse(
          "List<Any>"
        )
      )
    )
    Tester(
      s"List[List[java.lang.String]]",
      translate(
        parse(
          "List<List<String>>"
        )
      )
    )
  }

}
