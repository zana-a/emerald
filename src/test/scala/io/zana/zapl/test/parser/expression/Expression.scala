package io.zana.zapl.test.parser.expression

import io.zana.zapl.structure.expression.{Pair, Single}
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.{primitive, statics}
import io.zana.zapl.test.parser.Tester
import org.junit.Test

class Expression extends Base {

  @Test
  def digit(): Unit = {
    Tester(
      Tools.parse,
      "1",
      primitive.Integer(1)
    )
  }

  @Test
  def not(): Unit = {
    Tester(
      Tools.parse,
      "!true",
      Single("!", primitive.Boolean(true))
    )
    Tester(
      Tools.parse,
      "!(true && false)",
      Single("!", Pair("&&", primitive.Boolean(true), primitive.Boolean(false)))
    )
  }

  @Test
  def negate(): Unit = {
    Tester(
      Tools.parse,
      "-1",
      Single("-", primitive.Integer(1))
    )
  }

  @Test
  def arithmetic(): Unit = {
    Tester(
      Tools.parse,
      "1 + 2",
      Pair(
        "+",
        primitive.Integer(1),
        primitive.Integer(2)
      )
    )
    Tester(
      Tools.parse,
      "(1 / 2 + 2)",
      Pair(
        "+",
        Pair(
          "/",
          primitive.Integer(1),
          primitive.Integer(2),
        ),
        primitive.Integer(2)
      )
    )
    Tester(
      Tools.parse,
      "(1 / 2 + 2) * 2",
      Pair(
        "*",
        Pair(
          "+",
          Pair(
            "/",
            primitive.Integer(1),
            primitive.Integer(2),
          ),
          primitive.Integer(2)
        ),
        primitive.Integer(2)
      )
    )
  }

  @Test
  def concat(): Unit = {
    Tester(
      Tools.parse,
      "\"a\" + \"b\"",
      Pair("+", primitive.String("\"a\""), primitive.String("\"b\""))
    )
  }

  @Test
  def logic(): Unit = {
    Tester(
      Tools.parse,
      "true",
      primitive.Boolean(true)
    )
    Tester(
      Tools.parse,
      "true == true",
      Pair("==", primitive.Boolean(true), primitive.Boolean(true))
    )
    Tester(
      Tools.parse,
      "true || false",
      Pair("||", primitive.Boolean(true), primitive.Boolean(false))
    )
    Tester(
      Tools.parse,
      "true && false",
      Pair("&&", primitive.Boolean(true), primitive.Boolean(false))
    )
  }

  @Test
  def relational(): Unit = {
    Tester(
      Tools.parse,
      "1 < 2",
      Pair("<", primitive.Integer(1), primitive.Integer(2))
    )
    Tester(
      Tools.parse,
      "1 > 2",
      Pair(">", primitive.Integer(1), primitive.Integer(2))
    )
    Tester(
      Tools.parse,
      "1 <= 2",
      Pair("<=", primitive.Integer(1), primitive.Integer(2))
    )
    Tester(
      Tools.parse,
      "1 >= 2",
      Pair(">=", primitive.Integer(1), primitive.Integer(2))
    )
  }

}
