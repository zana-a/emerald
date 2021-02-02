package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestList {
  @Test
  def testEmptyList(): Unit = {
    val subject =
      """
        |[]
        |"""
        .stripMargin
        .trim

    testList(subject, structure.primitive.List(List()))
  }

  @Test
  def testNestedList(): Unit = {
    val subject =
      """
        |[[]]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.List(List())
      ))
    )
  }

  @Test
  def testStringList(): Unit = {
    val subject =
      """
        |["hello", "world"]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.String("\"hello\""),
        structure.primitive.String("\"world\"")
      ))
    )
  }

  @Test
  def testBooleanList(): Unit = {
    val subject =
      """
        |[true,false]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.Boolean(true),
        structure.primitive.Boolean(false)
      ))
    )
  }

  @Test
  def testIntegerList(): Unit = {
    val subject =
      """
        |[1,2,3]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.Integer(1),
        structure.primitive.Integer(2),
        structure.primitive.Integer(3)
      ))
    )
  }

  def testList(input: String, expected: structure.primitive.List): Unit = {
    parser.Base.parse(Primitive.list, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testListList(): Unit = {
    val subject =
      """
        |[[],[],[]]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.List(List()),
        structure.primitive.List(List()),
        structure.primitive.List(List())
      ))
    )
  }

  @Test
  def testSpacedList(): Unit = {
    val subject =
      """
        |[  [
        |]     ,
        |[
        |]
        |   ,
        |      [
        |      ]
        |      ]
        |"""
        .stripMargin
        .trim

    testList(
      subject,
      structure.primitive.List(List(
        structure.primitive.List(List()),
        structure.primitive.List(List()),
        structure.primitive.List(List())
      ))
    )
  }
}
