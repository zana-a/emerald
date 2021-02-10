//package io.zana.zapl.test.parser.program
//
//import io.zana.zapl.structure.variable.Variable
//import io.zana.zapl.{parser, structure, test}
//import org.junit.Test
//
//class TestProgram {
//
//  import parser.program.{Program => Parser}
//  import structure.block.Block
//  import structure.call.{FunctionCall, ModuleCall}
//  import structure.comment.LineComment
//  import structure.common.Identifier
//  import structure.function.Function
//  import structure.module.Module
//  import structure.primitive
//  import structure.program.{Program => Structure}
//  import test.parser.Tester
//
//  @Test
//  def empty_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala()
//      )
//    )
//  }
//
//  @Test
//  def comment_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |# comment
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          LineComment(" comment")
//        )
//      )
//    )
//  }
//
//  @Test
//  def module_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |mod A do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Module(
//            name = Identifier("A"),
//            body = List.scala()
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |mod A do
//        |
//        |end
//        |
//        |mod B do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Module(
//            name = Identifier("A"),
//            body = List.scala()
//          ),
//          Module(
//            name = Identifier("B"),
//            body = List.scala()
//          )
//        )
//      )
//    )
//  }
//
//  @Test
//  def function_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |def f() = 1
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = primitive.Integer(1)
//          )
//        )
//      )
//    )
//
//    //    Tester.tester(
//    //      Parser.program,
//    //      """
//    //        |def f() = 1 + 2
//    //        |"""
//    //        .stripMargin,
//    //      Structure(
//    //        statements = List.scala(
//    //          Function(
//    //            name = Identifier("f"),
//    //            params = List.scala(),
//    //            body = ???
//    //          )
//    //        )
//    //      )
//    //    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |def f() = something
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = Identifier("something")
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |def f() = do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = Block(List.scala())
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |def f() = g()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = FunctionCall(
//              name = Identifier("g"),
//              params = List.scala()
//            )
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |def f() = A::g()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = ModuleCall(
//              modules = List.scala(
//                Identifier("A")
//              ),
//              caller = FunctionCall(
//                name = Identifier("g"),
//                params = List.scala()
//              )
//            )
//          )
//        )
//      )
//    )
//  }
//
//  @Test
//  def call_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |f()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          FunctionCall(
//            name = Identifier("f"),
//            params = List.scala()
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |A::f()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          ModuleCall(
//            modules = List.scala(
//              Identifier("A")
//            ),
//            caller = FunctionCall(
//              name = Identifier("f"),
//              params = List.scala()
//            )
//          )
//        )
//      )
//    )
//  }
//
//  @Test
//  def variable_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |item = 1
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = primitive.Integer(1)
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |item = true
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = primitive.Boolean(true)
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |item = "test"
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = primitive.String.scala("\"test\"")
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |item = []
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = primitive.List.scala(List.scala())
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |item = f()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = FunctionCall(
//              name = Identifier("f"),
//              params = List.scala()
//            )
//          )
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |item = A::f()
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          Variable(
//            name = Identifier("item"),
//            body = ModuleCall(
//              modules = List.scala(
//                Identifier("A")
//              ),
//              caller = FunctionCall(
//                name = Identifier("f"),
//                params = List.scala()
//              )
//            )
//          )
//        )
//      )
//    )
//  }
//
//  @Test
//  def control_in_program(): Unit = {
//    Tester.tester(
//      Parser.program,
//      """
//        |cond do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          ???
//        )
//      )
//    )
//
//    Tester.tester(
//      Parser.program,
//      """
//        |loop do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        statements = List.scala(
//          ???
//        )
//      )
//    )
//  }
//}
