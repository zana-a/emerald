package io.zana.zapl.parser.primitive

import io.zana.zapl.parser.Base._
import io.zana.zapl.parser.Primitive
import org.junit.Test


class BooleanTest {

  def template(s: String, shouldFail: Boolean, errorMessage: String = "") = {
    if (shouldFail) {
      parse(Primitive.boolean, s) match {
        case Success(s, _) =>
          assert(false, s"\n\tFailure: ${s}\n\tMessage:${errorMessage}")

        case Failure(f, _) =>
          assert(true)

        case Error(e, _) =>
          assert(false, s"\n\tError: $e")
      }
    } else {
      parse(Primitive.boolean, s) match {
        case Success(s, _) =>
          assert(true) // Success: Parsed -> $s

        case Failure(f, _) =>
          assert(false, s"\n\tFailure: ${f}\n\tMessage:${errorMessage}")

        case Error(e, _) =>
          assert(false, s"\n\tError: $e")
      }
    }
  }

  @Test
  def testTrue = template("true", false)

  @Test
  def testFalse = template("false", false)

  @Test
  def testUppercaseFalse = template("FALSE", true)

  @Test
  def testUppercaseTrue = template("TRUE", true)

  @Test
  def testCapitalFalse = template("False", true)

  @Test
  def testCaptialTrue = template("True", true)

  @Test
  def testRandomFalse = template("fAlSe", true)

  @Test
  def testRandomTrue = template("tRuE", true)

  @Test
  def testRepeatedTrue = template("truetrue", true)

  @Test
  def testRepeatedFalse = template("falsefalse", true)
}
