package io.zana.zapl.test.parser.primitive

import org.junit.Test
import io.zana.zapl.test.parser.Tester
import io.zana.zapl.structure.primitive
import io.zana.zapl.structure.call
import io.zana.zapl.structure.identifier
import io.zana.zapl.structure.identifier.Identifier

class Primitive extends Base {

  @Test
  def integer(): Unit = {
    Tester(
      Tools.parser,
      "1",
      primitive.Integer(1),
    )
    Tester(
      Tools.parser,
      "123",
      primitive.Integer(123),
    )
  }

  @Test
  def boolean(): Unit = {
    Tester(
      Tools.parser,
      "true",
      primitive.Boolean(true),
    )
    Tester(
      Tools.parser,
      "false",
      primitive.Boolean(false),
    )
  }

  @Test
  def string(): Unit = {
    Tester(
      Tools.parser,
      "\"hello\"",
      primitive.String("\"hello\""),
    )
    Tester(
      Tools.parser,
      "\"hello world!\"",
      primitive.String("\"hello world!\""),
    )
  }

  @Test
  def list(): Unit = {
    // Empty List
    Tester(
      Tools.parser,
      "[]",
      primitive.List(List())
    )

    // 2D List
    Tester(
      Tools.parser,
      "[[]]",
      primitive.List(List(
        primitive.List(List()),
      ))
    )

    // 3D List
    Tester(
      Tools.parser,
      "[[[]]]",
      primitive.List(List(
        primitive.List(List(
          primitive.List(List())
        )),
      ))
    )

    // nD List
    Tester(
      Tools.parser,
      "[[[[[[[[[]]]]]]]]]",
      primitive.List(List(
        primitive.List(List(
          primitive.List(List(
            primitive.List(List(
              primitive.List(List(
                primitive.List(List(
                  primitive.List(List(
                    primitive.List(List(
                      primitive.List(List())
                    )),
                  ))
                )),
              ))
            )),
          ))
        )),
      ))
    )

    // Aggregated
    Tester(
      Tools.parser,
      "[1, 123, true, false, [], \"hello\", a]",
      primitive.List(List(
        primitive.Integer(1),
        primitive.Integer(123),
        primitive.Boolean(true),
        primitive.Boolean(false),
        primitive.List(List()),
        primitive.String("\"hello\""),
        Identifier("a"),
      ))
    )
    Tester(
      Tools.parser,
      """
        |[1, [2, [3, [4], 3], 2], 1]
        |""".stripMargin,
      primitive.List(List(
        primitive.Integer(1),
        primitive.List(List(
          primitive.Integer(2),
          primitive.List(List(
            primitive.Integer(3),
            primitive.List(List(
              primitive.Integer(4),
            )),
            primitive.Integer(3),
          )),
          primitive.Integer(2),
        )),
        primitive.Integer(1),
      )),
    )

    // Function Call in list
    Tester(
      Tools.parser,
      """
        |[hello()]
        |""".stripMargin,
      primitive.List(List(
        call.FunctionCall(
          identifier.Identifier("hello"),
          List()
        ))
      )
    )
    Tester(
      Tools.parser,
      """
        |[A::hello()]
        |""".stripMargin,
      primitive.List(List(
        call.ModuleCall(
          List(
            identifier.Identifier("A")
          ),
          call.FunctionCall(
            identifier.Identifier("hello"),
            List()
          )
        )
      ))
    )
    Tester(
      Tools.parser,
      """
        |[a, b, c]
        |""".stripMargin,
      primitive.List(List(
        Identifier("a"),
        Identifier("b"),
        Identifier("c"),
      ))
    )
  }

  // TODO:
  // Test for:
  // 1. expression within list
  // 3. Variable within list

}
