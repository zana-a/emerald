package io.zana.zapl


import io.zana.zapl.ast.Expression._
import io.zana.zapl.ast.Statement._
import io.zana.zapl.ast.Type._


object Main {

  def main(args: Array[java.lang.String]): Unit = {
    val program = Append(
      scala.List(
        If(
          scala.List(
            Guard(
              Math.Expression(
                scala.List(
                  Integer(32),
                  Operator.+,
                  Integer(14)
                )
              ),
              Skip
            ),
            Guard(
              Logic.Expression(
                scala.List(
                  Bool(true),
                  Operator.&&,
                  Bool(false)
                )
              ),
              Skip
            )
          )
        )
      )
    )

    print(program)
  }
}
