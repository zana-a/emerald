package io.zana.zapl

import io.zana.zapl.standard.{Compiler, Error}

import scala.util.control.Breaks.{break, breakable}

object Main extends App {
  //  Compiler(args, display = true)


  println(
    translator.control.Cond(
      parser.base.Base.parse(parser.control.Cond.apply,
        """
          |cond do
          | true => false
          | _ => do
          |   1 + 2
          | end
          |end
          |""".stripMargin) match {
        case parser.base.Base.Success(s, _) => s
        case parser.base.Base.Failure(s, _) => Error(Error.UnknownSyntax(s))
        case parser.base.Base.Error(s, _) => Error(Error.UnknownSyntax(s))
      }
    )
  )


  //  val list = List(1, 2, 3)
  //  var counter = 0
  //  breakable {
  //    def loop01(): Any = if (list.size - 1 == counter) {
  //      print(list(counter))
  //      break
  //      loop01()
  //    } else {
  //      print(list(counter))
  //      counter = counter + 1
  //      loop01()
  //    }
  //
  //    loop01()
  //  }
}

//# Iterate Example
//# ===============
//#
//# This example shows how to iterate through a list of items using the loop
//# control pattern.
//#
//# Although it is clunky, it uses mutable state of a counter to record where the
//# program is at a given point.
//#
//
//let items: List<Any> = [1, 2, 3, "String"]
//let mut counter: Int = 0
//
//loop do
//  Enum::size(items) == counter => do
//    IO::puts(
//      Enum::at(
//        items,
//        counter
//      )
//    )
//    break
//  end
//
//  _ => do
//    IO::puts(
//      Enum::at(
//        items,
//        counter
//      )
//    )
//    counter = counter + 1
//    break
//  end
//end
