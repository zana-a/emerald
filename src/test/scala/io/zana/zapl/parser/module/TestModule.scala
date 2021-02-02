package io.zana.zapl.parser.module

import io.zana.zapl.parser.module
import io.zana.zapl.{parser, structure}
import org.junit.Assert._
import org.junit.Test

class TestModule {

  @Test
  def testEmptyModule(): Unit = {
    val subject =
      """
        |mod A do
        |
        |end
        |"""
        .stripMargin
        .trim

    testModule(
      subject,
      structure.module.Module(
        structure.common.Identifier("A"),
        List()
      )
    )
  }

  @Test
  def testSingleFunctionModule(): Unit = {
    val subject =
      """
        |mod A do
        |  def f() = do
        |
        |  end
        |end
        |"""
        .stripMargin
        .trim

    testModule(
      subject,
      structure.module.Module(
        structure.common.Identifier("A"),
        List(
          structure.function.Function(
            structure.common.Identifier("f"),
            List(),
            structure.block.Block(
              List()
            )
          )
        )
      )
    )
  }

  @Test
  def testMultiFunctionModule(): Unit = {
    val subject =
      """
        |mod A do
        |  def f() = do
        |
        |  end
        |
        |  def e() = do
        |
        |  end
        |
        |  def g() = do
        |
        |  end
        |end
        |"""
        .stripMargin
        .trim

    testModule(
      subject,
      structure.module.Module(
        structure.common.Identifier("A"),
        List(
          structure.function.Function(
            structure.common.Identifier("f"),
            List(),
            structure.block.Block(
              List()
            )
          ),
          structure.function.Function(
            structure.common.Identifier("e"),
            List(),
            structure.block.Block(
              List()
            )
          ),
          structure.function.Function(
            structure.common.Identifier("g"),
            List(),
            structure.block.Block(
              List()
            )
          )
        )
      )
    )
  }

  @Test
  def testNestedModule(): Unit = {
    val subject =
      """
        |mod A do
        |  mod B do
        |    mod C do
        |
        |    end
        |  end
        |end
        |"""
        .stripMargin
        .trim

    testModule(
      subject,
      structure.module.Module(
        structure.common.Identifier("A"),
        List(
          structure.module.Module(
            structure.common.Identifier("B"),
            List(
              structure.module.Module(
                structure.common.Identifier("C"),
                List()
              )
            )
          )
        )
      )
    )
  }

  def testModule(input: String, expected: structure.module.Module): Unit = {
    parser.Base.parse(module.Module.module, input) match {
      case parser.Base.Success(s, _) => assertEquals(expected, s)
      case parser.Base.Failure(s, _) => assert(assertion = false, s)
      case parser.Base.Error(s, _) => assert(assertion = false, s)
    }
  }

  @Test
  def testNestedFunctionInModule(): Unit = {
    val subject =
      """
        |mod A do
        |  def a() = do
        |  end
        |
        |  mod B do
        |    def b() = do
        |    end
        |
        |    mod C do
        |      def c() = do
        |      end
        |    end
        |
        |    def bb() = do
        |    end
        |  end
        |
        |  def aa() = do
        |  end
        |end
        |"""
        .stripMargin
        .trim

    testModule(
      subject,
      structure.module.Module(
        structure.common.Identifier("A"),
        List(
          structure.function.Function(
            structure.common.Identifier("a"),
            List(),
            structure.block.Block(List())
          ),
          structure.module.Module(
            structure.common.Identifier("B"),
            List(
              structure.function.Function(
                structure.common.Identifier("b"),
                List(),
                structure.block.Block(List())
              ),
              structure.module.Module(
                structure.common.Identifier("C"),
                List(
                  structure.function.Function(
                    structure.common.Identifier("c"),
                    List(),
                    structure.block.Block(List())
                  ),
                )
              ),
              structure.function.Function(
                structure.common.Identifier("bb"),
                List(),
                structure.block.Block(List())
              ),
            )
          ),
          structure.function.Function(
            structure.common.Identifier("aa"),
            List(),
            structure.block.Block(List())
          ),
        )
      )
    )
  }
}
