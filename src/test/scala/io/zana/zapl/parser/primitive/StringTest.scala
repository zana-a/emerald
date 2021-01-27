package io.zana.zapl.parser.primitive

import io.zana.zapl.parser.Primitive
import org.junit.Assert._
import org.junit.Test

class StringTest {

  def template(s: String, errorMessage: String = "") = {
    import io.zana.zapl.parser.Base._

    parse(Primitive.string, "\"" + s + "\"") match {
      case Success(s, _) =>
        assert(true) // Success: Parsed -> $s

      case Failure(f, _) =>
        assert(false, s"Failure: ${f}\n\tMessage:${errorMessage}")

      case Error(e, _) =>
        assert(false, s"\n\tError: $e")
    }
  }

  @Test
  def testEmptyString =
    template("")

  @Test
  def testStringNoSpace =
    template("hello")

  @Test
  def testStringWithSpace =
    template("hello world")

  @Test
  def testStringWithSpecialCharacters =
    template("hello, world! where shall we eat @?")

  @Test
  def testStringWithEscapedCharacters =
    template("\"")
    template("\'")
    template("\\")
    template("\\\"\'\n\t")
}

