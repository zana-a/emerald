package io.zana.zapl.parser

import io.zana.zapl.parser.Parser.Operator
import io.zana.zapl.structure.{Block, Expression, Type}
import org.junit.Assert._
import org.junit.Test

class Block {

  def result(source: String): Either[Any, String] = {
    val result = Parser.parse(Parser.Block.block, source)
    result match {
      case Parser.Success(result, _) => Left(result)
      case Parser.Failure(msg, _) => Right("Failure: " + msg)
      case Parser.Error(msg, _) => Right("Error: " + msg)
    }
  }

  @Test
  def `test empty block`(): Unit = {
    val source = "do end"
    val expected = Block(List())

    result(source) match {
      case Left(result) => assertEquals(expected, result)
      case Right(error) => assert(assertion = false, error)
    }
  }

  @Test
  def `test expression block`(): Unit = {
    {
      val source = "do 1 end"
      val expected = Block(List(Expression.Expression(List(Type.Integer(1)))))

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }

    {
      val source = "do 1 + 1 end"
      val expected =
        Block(
          List(
            Expression.Expression(
              List(
                Type.Integer(1),
                Operator.PLUS,
                Type.Integer(1)
              )
            )
          )
        )

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }

    {
      val source = "do (1 + 1) + 1 end"
      val expected =
        Block(
          List(
            Expression.Expression(
              List(
                Expression.Grouping(
                  List(
                    Type.Integer(1),
                    Operator.PLUS,
                    Type.Integer(1)
                  ),
                ),
                Operator.PLUS,
                Type.Integer(1)
              )
            )
          )
        )

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }

    {
      val source = "do true end"
      val expected = Block(List(Type.Boolean(true)))

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }

    {
      val source = "do true && false end"
      val expected = Block(List(Type.Boolean(true), Operator.AND, Type.Boolean(false)))

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }

    {
      val source = "do [1, 2, 3] end"
      val expected = Block(List(Type.List(List(1, 2, 3))))

      result(source) match {
        case Left(result) => assertEquals(expected, result)
        case Right(error) => assert(assertion = false, error)
      }
    }
  }

  // TODO: test do-block with the body of function, control and call each
}