//package io.zana.zapl.test.parser.function
//
//import io.zana.zapl.structure.call.FunctionCall
//import io.zana.zapl.{parser, structure, test}
//import org.junit.Test
//
//class TestFunction {
//
//  import parser.function.{Function => Parser}
//  import structure.block.Block
//  import structure.common.Identifier
//  import structure.function.{Function => Structure}
//  import structure.primitive
//  import test.parser.Tester
//
//  @Test
//  def function_type_body(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = 1
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = primitive.Integer(1)
//      )
//    )
//
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = "test"
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = primitive.String("\"test\"")
//      )
//    )
//
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = true
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = primitive.Boolean(true)
//      )
//    )
//
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = []
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = primitive.List(List())
//      )
//    )
//  }
//
//  @Test
//  def function_block_body(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = Block(List())
//      )
//    )
//  }
//
//  @Test
//  def function_identifier_body(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = something
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = Identifier("something")
//      )
//    )
//  }
//
//  @Test
//  def function_expression_body(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = 1 + 2
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = ???
//      )
//    )
//  }
//
//  @Test
//  def function_call_body(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f() = a()
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(),
//        body = FunctionCall(
//          name = Identifier("a"),
//          params = List()
//        )
//      )
//    )
//  }
//
//  @Test
//  def function_params(): Unit = {
//    Tester.tester(
//      Parser.function,
//      """
//        |def f(a) = a
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(
//          Identifier("a")
//        ),
//        body = Identifier("a")
//      )
//    )
//
//    Tester.tester(
//      Parser.function,
//      """
//        |def f(a, b, c) = a
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("f"),
//        params = List(
//          Identifier("a"),
//          Identifier("b"),
//          Identifier("c")
//        ),
//        body = Identifier("a")
//      )
//    )
//  }
//}
