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
              Math.Express(
                scala.List(
                  Integer(32),
                  Operator.+,
                  Integer(14)
                )
              ),
              Skip
            ),
            Guard(
              Logic.Express(
                scala.List(
                  Bool(true),
                  Operator.&&,
                  Bool(false)
                )
              ),
              Skip
            )
          )
        ),

        If(
          scala.List(
            Guard(
              Math.Express(
                scala.List(
                  Integer(32),
                  Operator.+,
                  Integer(14)
                )
              ),
              Skip
            ),
            Guard(
              Logic.Express(
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

    val b = for {
      statement <- program.statements
    } yield println(statement)

    val c = for {
      i <- b
    } yield println(i)

    // Result:
    // If(List(Guard(Express(List(Integer(32), +, Integer(14))),Skip), Guard(Express(List(Bool(true), &&, Bool(false))),Skip)))
    // If(List(Guard(Express(List(Integer(32), +, Integer(14))),Skip), Guard(Express(List(Bool(true), &&, Bool(false))),Skip)))
  }
}
