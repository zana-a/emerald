//package io.zana.zapl.test.parser.module
//
//import io.zana.zapl.{parser, structure, test}
//import org.junit.Test
//
//class TestModule {
//
//  import parser.module.{Module => Parser}
//  import structure.common.Identifier
//  import structure.function.Function
//  import structure.module.{Module => Structure}
//  import structure.primitive
//  import test.parser.Tester
//
//  @Test
//  def empty_module(): Unit = {
//    Tester.tester(
//      Parser.module,
//      """
//        |mod A do
//        |
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("A"),
//        body = List.scala()
//      )
//    )
//  }
//
//  @Test
//  def single_item_module(): Unit = {
//    Tester.tester(
//      Parser.module,
//      """
//        |mod A do
//        |  def f() = 1
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("A"),
//        body = List.scala(
//          Function(
//            name = Identifier("f"),
//            params = List.scala(),
//            body = primitive.Integer(1)
//          )
//        )
//      )
//    )
//  }
//
//  @Test
//  def multi_item_module(): Unit = {
//    Tester.tester(
//      Parser.module,
//      """
//        |mod A do
//        |  def a() = 1
//        |  def b() = 1
//        |  def c() = 1
//        |end
//        |"""
//        .stripMargin,
//      Structure(
//        name = Identifier("A"),
//        body = List.scala(
//          Function(
//            name = Identifier("a"),
//            params = List.scala(),
//            body = primitive.Integer(1)
//          ),
//          Function(
//            name = Identifier("b"),
//            params = List.scala(),
//            body = primitive.Integer(1)
//          ),
//          Function(
//            name = Identifier("c"),
//            params = List.scala(),
//            body = primitive.Integer(1)
//          )
//        )
//      )
//    )
//  }
//}
