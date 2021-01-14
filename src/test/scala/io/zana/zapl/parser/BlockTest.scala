package io.zana.zapl.parser

import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class BlockTest {

	@Test
	def `test empty block`(): Unit = {
		val source = "do end"
		val expected = structure.Block(List())

		result(source) match {
			case Left(result) => assertEquals(expected, result)
			case Right(error) => assert(assertion = false, error)
		}
	}

	def result(source: String): Either[Any, String] = {
		val result = Base.parse(parser.Block.block, source)
		result match {
			case Base.Success(result, _) => Left(result)
			case Base.Failure(msg, _) => Right("Failure: " + msg)
			case Base.Error(msg, _) => Right("Error: " + msg)
		}
	}

	@Test
	def `test expression block`(): Unit = {
		{
			val source = "do 1 end"
			val expected = structure.Block(
				List(structure.Expression.Expression(
					List(structure.Type.Integer(1)))
				)
			)

			result(source) match {
				case Left(result) => assertEquals(expected, result)
				case Right(error) => assert(assertion = false, error)
			}
		}

		{
			val source = "do 1 + 1 end"
			val expected =
				structure.Block(
					List(
						structure.Expression.Expression(
							List(
								structure.Type.Integer(1),
								parser.Operator.PLUS,
								structure.Type.Integer(1)
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
				structure.Block(
					List(
						structure.Expression.Expression(
							List(
								structure.Expression.Grouping(
									List(
										structure.Type.Integer(1),
										parser.Operator.PLUS,
										structure.Type.Integer(1)
									),
								),
								parser.Operator.PLUS,
								structure.Type.Integer(1)
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
			val expected = structure.Block(List(structure.Type.Boolean(true)))

			result(source) match {
				case Left(result) => assertEquals(expected, result)
				case Right(error) => assert(assertion = false, error)
			}
		}

		{
			val source = "do true && false end"
			val expected = structure.Block(
				List(
					structure.Type.Boolean(true),
					parser.Operator.AND,
					structure.Type.Boolean(false)
				)
			)

			result(source) match {
				case Left(result) => assertEquals(expected, result)
				case Right(error) => assert(assertion = false, error)
			}
		}

		{
			val source = "do [1, 2, 3] end"
			val expected = structure.Block(
				List(
					structure.Type.List(
						List(1, 2, 3)
					)
				)
			)

			result(source) match {
				case Left(result) => assertEquals(expected, result)
				case Right(error) => assert(assertion = false, error)
			}
		}
	}

	// TODO: test do-block with the body of function, control and call each
}