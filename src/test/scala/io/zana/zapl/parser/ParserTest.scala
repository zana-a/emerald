//package io.zana.zapl.parser
//
//import io.zana.zapl.ast.{Expression, Statement, Type}
//import io.zana.zapl.parser.Parser
//import org.junit.Assert._
//import org.junit._
//
//class IdentifierTest {
//
//  @Test
//  def `alpha` = {
//    val result = Parser.parse(Parser.identifier, "a")
//    result match {
//      case Parser.Success(result, _) => assertEquals("a", result)
//      case Parser.Failure(msg, _) => assert(false, msg)
//      case Parser.Error(msg, _) => assert(false, msg)
//    }
//  }
//
//  @Test
//  def `alpha followed by numeric` = {
//    val result = Parser.parse(Parser.identifier, "a1")
//    result match {
//      case Parser.Success(result, _) => assertEquals("a1", result)
//      case Parser.Failure(msg, _) => assert(false, msg)
//      case Parser.Error(msg, _) => assert(false, msg)
//    }
//  }
//
//  @Test
//  def `numeric followed by alpha` = {
//    val result = Parser.parse(Parser.identifier, "1a")
//    result match {
//      case Parser.Success(result, _) => assert(false, result)
//      case Parser.Failure(msg, _) => assert(true, msg)
//      case Parser.Error(msg, _) => assert(false, msg)
//    }
//  }
//
//  @Test
//  def `many mixed alphanumeric` = {
//    val result = Parser.parse(
//      Parser.identifier,
//      "a1b2"
//    )
//    result match {
//      case Parser.Success(result, _) => assert(true, result)
//      case Parser.Failure(msg, _) => assert(false, msg)
//      case Parser.Error(msg, _) => assert(false, msg)
//    }
//  }
//}
//
//class DefinitionTest {
//
//  @Test
//  def `lone identifier` = {
//    val result = Parser.parse(Parser.definition, "someStatement.Definition")
//
//    result match {
//      case Parser.Success(result, _) => assert(false, result)
//      case Parser.Failure(msg, _) => assert(true, msg)
//      case Parser.Error(msg, _) => assert(false, msg)
//    }
//  }
//
//  @Test
//  def `definition of an integer` = {
//    {
//      val result = {
//        Parser.parse(Parser.definition, "someStatement.Definition := 1") match {
//          case Parser.Success(result, _) => {
//            result
//          }
//          case Parser.Failure(msg, _) => assert(false, msg)
//          case Parser.Error(msg, _) => assert(false, msg)
//        }
//      }
//
//      assertEquals(Statement.Definition("someStatement.Definition", Type.Integer(1)), result)
//    }
//    {
//      val result = {
//        Parser.parse(Parser.definition, "someStatement.Definition := 0123") match {
//          case Parser.Success(result, _) => {
//            assert(false, result)
//          }
//          case Parser.Failure(msg, _) => assert(true, msg)
//          case Parser.Error(msg, _) => assert(false, msg)
//        }
//      }
//    }
//    {
//      val result = {
//        Parser.parse(Parser.definition, "someStatement.Definition := 2147483647") match {
//          case Parser.Success(result, _) => {
//            result
//          }
//          case Parser.Failure(msg, _) => assert(false, msg)
//          case Parser.Error(msg, _) => assert(false, msg)
//        }
//      }
//
//      assertEquals(Statement.Definition("someStatement.Definition", Type.Integer(2147483647)), result)
//    }
//    //    {
//    //      // TODO: How to handle??
//    //      val result = {
//    //        Parser.parse(Parser.definition, "someStatement.Definition := 214748364123") match {
//    //          case Parser.Success(result, _) => {
//    //            assert(false, result)
//    //          }
//    //          case Parser.Failure(msg, _) => assert(true, msg)
//    //          case Parser.Error(msg, _) => assert(false, msg)
//    //        }
//    //      }
//    //    }
//  }
//}