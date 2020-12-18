package io.zana.zapl.translator

import io.zana.zapl.ast.{Expression, _}
import io.zana.zapl.translator.Translator.Generate
import org.junit.Assert._
import org.junit.Test

class TranslatorTest {

  @Test
  def `definition for integer` = {

    assertEquals("var a = 1",
      Generate.definition(
        Expression.Definition("a", Type.Integer(1)),
      )
    )

    assertEquals("var a = 2",
      Generate.definition(
        Expression.Definition("a", Type.Integer(2)),
      )
    )

    assertEquals("var a = 3",
      Generate.definition(
        Expression.Definition("a", Type.Integer(3)),
      )
    )

    assertEquals("var a = 4",
      Generate.definition(
        Expression.Definition("a", Type.Integer(4)),
      )
    )

    assertEquals("var a = 5",
      Generate.definition(
        Expression.Definition("a", Type.Integer(5)),
      )
    )

    assertEquals("var a = 6",
      Generate.definition(
        Expression.Definition("a", Type.Integer(6)),
      )
    )

    assertEquals("var a = 7",
      Generate.definition(
        Expression.Definition("a", Type.Integer(7)),
      )
    )

    assertEquals("var a = 8",
      Generate.definition(
        Expression.Definition("a", Type.Integer(8)),
      )
    )

    assertEquals("var a = 9",
      Generate.definition(
        Expression.Definition("a", Type.Integer(9)),
      )
    )

    assertEquals("var a = 0",
      Generate.definition(
        Expression.Definition("a", Type.Integer(0)),
      )
    )

    assertEquals("var a = 2147483647",
      Generate.definition(
        Expression.Definition("a", Type.Integer(2147483647))
      )
    )
  }

  @Test
  def `definition for string` = {
    assertEquals("var a = \"\"", Generate.definition(
      Expression.Definition("a", Type.String("")),
    ))

    assertEquals("var a = \"a\"", Generate.definition(
      Expression.Definition("a", Type.String("a")),
    ))

    assertEquals("var a = \"abcde\"", Generate.definition(
      Expression.Definition("a", Type.String("abcde")),
    ))

    assertEquals("var a = \"abcde abcde\"", Generate.definition(
      Expression.Definition("a", Type.String("abcde abcde")),
    ))

    assertEquals("var a = \"a\ta\"", Generate.definition(
      Expression.Definition("a", Type.String("a\ta")),
    ))

    assertEquals("var a = \"a\na\"", Generate.definition(
      Expression.Definition("a", Type.String("a\na")),
    ))

    assertEquals("var a = \"!=@#¡+)\"", Generate.definition(
      Expression.Definition("a", Type.String("!=@#¡+)")),
    ))
  }

  @Test
  def `definition for bool` = {
    assertEquals("var a = true",
      Generate.definition(
        Expression.Definition("a", Type.Bool(true)),
      )
    )

    assertEquals("var a = false",
      Generate.definition(
        Expression.Definition("a", Type.Bool(false)),
      )
    )
  }

  @Test
  def `definition for list` = {
    assertEquals("var a = List()",
      Generate.definition(
        Expression.Definition("a", Type.List(List())),
      )
    )

    assertEquals("var a = List(1)",
      Generate.definition(
        Expression.Definition(
          "a",
          Type.List(
            List(Type.Integer(1)),
          ),
        ),
      ),
    )

    assertEquals("var a = List(1, 2, 3)",
      Generate.definition(
        Expression.Definition(
          "a",
          Type.List(
            List(
              Type.Integer(1),
              Type.Integer(2),
              Type.Integer(3),
            ),
          ),
        ),
      ),
    )

    assertEquals("var a = List(1, \"a b c d\", List(true, false))",
      Generate.definition(
        Expression.Definition(
          "a",
          Type.List(
            List(
              Type.Integer(1),
              Type.String("a b c d"),
              Type.List(
                List(
                  Type.Bool(true),
                  Type.Bool(false)
                )
              ),
            ),
          ),
        ),
      ),
    )
  }
}
