package io.zana.zapl.parser.call

import org.junit.Assert._
import org.junit.Test

class TestCall {

  def testCall(input: String, expected: List[Any]) = {

    import io.zana.zapl.parser.Base._
    import io.zana.zapl.parser.program._

    parse(Program.build, input) match {
      case Success(s, _) => assertEquals(expected, s)
      case Failure(s, _) => assert(false, s)
      case Error(s, _) => assert(false, s)
    }
  }

  @Test
  def testFunctionCallNoParam = {

    import io.zana.zapl.structure.call
    import io.zana.zapl.structure.common._

    val subject =
      """
        |f()
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        call.Function(
          Identifier("f"),
          List(),
        )
      )
    )
  }

  @Test
  def testFunctionCallWithSingleParam = {

    import io.zana.zapl.structure.common._
    import io.zana.zapl.structure.{call, primitive}

    val subject =
      """
        |f(1)
        |f(true)
        |f(a)
        |f(e())
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        call.Function(
          Identifier("f"),
          List(
            primitive.Integer(1)
          ),
        ),
        call.Function(
          Identifier("f"),
          List(
            primitive.Boolean(true)
          ),
        ),
        call.Function(
          Identifier("f"),
          List(
            Identifier("a")
          ),
        ),
        //TODO Expression here
        call.Function(
          Identifier("f"),
          List(
            call.Function(
              Identifier("e"),
              List()
            )
          )
        )
      )
    )
  }

  @Test
  def testFunctionCallWithMultiParam = {

    import io.zana.zapl.structure.common._
    import io.zana.zapl.structure.{call, primitive}

    val subject =
      """
        |f(1, true, a, e(), A::f())
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        call.Function(
          Identifier("f"),
          List(
            primitive.Integer(1),
            primitive.Boolean(true),
            Identifier("a"),
            call.Function(
              Identifier("e"),
              List()
            ),
            call.Module(
              List(
                Identifier("A")
              ),
              call.Function(
                Identifier("f"),
                List()
              )
            )
          )
        )
      )
    )
  }

  @Test
  def testModuleFunctionCall = {

    import io.zana.zapl.structure.call
    import io.zana.zapl.structure.common.Identifier

    val subject =
      """
        |A::f()
        |A::B::f()
        |A::B::C::f()
        |"""
        .stripMargin
        .trim

    testCall(
      subject,
      List(
        call.Module(
          List(
            Identifier("A"),
          ),
          call.Function(
            Identifier("f"),
            List(),
          )
        ),
        call.Module(
          List(
            Identifier("A"),
            Identifier("B"),
          ),
          call.Function(
            Identifier("f"),
            List(),
          )
        ),
        call.Module(
          List(
            Identifier("A"),
            Identifier("B"),
            Identifier("C"),
          ),
          call.Function(
            Identifier("f"),
            List(),
          )
        ),
      )
    )
  }
}
