package io.zana.zapl.parser.primitive

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestString {
  @Test
  def testEmptyString = {
    val subject =
      """
        |""
        |"""
        .stripMargin
        .trim

    testString(subject, structure.primitive.String(subject))
  }

  def testString(input: String, expected: structure.primitive.String) = {
    parser.Base.parse(Primitive.string, input) match {
      case parser.Base.Success(result, _) => assertEquals(expected, result)
      case parser.Base.Failure(s, _) => assert(false, s)
      case parser.Base.Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testSpaceyString = {
    val subject =
      """
        |" hello world "
        |"""
        .stripMargin
        .trim

    testString(subject, structure.primitive.String(subject))
  }

  @Test
  def testEscapedInString = {
    val subject =
      """
        |"\"\\"
        |"""
        .stripMargin
        .trim

    testString(subject, structure.primitive.String(subject))
  }

  @Test
  def testSpecialCharactersInString = {
    val subjectOne = {
      """
        |"! # $ % & ' \" ( ) * + , - . / : ; < = > ? @ [ ] ^ _ ` { | } ~"
        |"""
        .stripMargin
        .trim
    }

    testString(subjectOne, structure.primitive.String(subjectOne))
  }

  @Test
  def testNumbersInString = {
    val subject = {
      """
        |"0 1 2 3 4 5 6 7 8 9"
        |"""
        .stripMargin
        .trim
    }
    testString(subject, structure.primitive.String(subject))
  }

  @Test
  def testUppercaseAlphaInString = {
    val subject = {
      """
        |"A B C D E F G H I J K L M N O P Q R S T U V W X Y Z "
        |"""
        .stripMargin
        .trim
    }

    testString(subject, structure.primitive.String(subject))
  }

  @Test
  def testLowercaseAlphaInString = {
    val subject = {
      """
        |"a b c d e f g h i j k l m n o p q r s t u v w x y z"
        |"""
        .stripMargin
        .trim
    }

    testString(subject, structure.primitive.String(subject))
  }
}

