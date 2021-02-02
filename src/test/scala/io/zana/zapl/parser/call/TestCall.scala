package io.zana.zapl.parser.call

import org.junit.Assert._
import org.junit.Test

class TestCall {

  def testCall(input: String, expected: List[Any]): Unit = {

    import io.zana.zapl.parser.Base._
    import io.zana.zapl.parser.program._

    parse(Program.build, input) match {
      case Success(s, _) => assertEquals(expected, s)
      case Failure(s, _) => assert(assertion = false, s)
      case Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testFunctionCallNoParam(): Unit = {

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
        call.FunctionCall(
          Identifier("f"),
          List(),
        )
      )
    )
  }

  @Test
  def testFunctionCallWithSingleParam(): Unit = {

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
        call.FunctionCall(
          Identifier("f"),
          List(
            primitive.Integer(1)
          ),
        ),
        call.FunctionCall(
          Identifier("f"),
          List(
            primitive.Boolean(true)
          ),
        ),
        call.FunctionCall(
          Identifier("f"),
          List(
            Identifier("a")
          ),
        ),
        //TODO Expression here
        call.FunctionCall(
          Identifier("f"),
          List(
            call.FunctionCall(
              Identifier("e"),
              List()
            )
          )
        )
      )
    )
  }

  @Test
  def testFunctionCallWithMultiParam(): Unit = {

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
        call.FunctionCall(
          Identifier("f"),
          List(
            primitive.Integer(1),
            primitive.Boolean(true),
            Identifier("a"),
            call.FunctionCall(
              Identifier("e"),
              List()
            ),
            call.ModuleCall(
              List(
                Identifier("A")
              ),
              call.FunctionCall(
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
  def testModuleFunctionCall(): Unit = {

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
        call.ModuleCall(
          List(
            Identifier("A"),
          ),
          call.FunctionCall(
            Identifier("f"),
            List(),
          )
        ),
        call.ModuleCall(
          List(
            Identifier("A"),
            Identifier("B"),
          ),
          call.FunctionCall(
            Identifier("f"),
            List(),
          )
        ),
        call.ModuleCall(
          List(
            Identifier("A"),
            Identifier("B"),
            Identifier("C"),
          ),
          call.FunctionCall(
            Identifier("f"),
            List(),
          )
        ),
      )
    )
  }
}
