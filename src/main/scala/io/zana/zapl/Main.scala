package io.zana.zapl

import scala.annotation.tailrec

//import java.nio.file.Path
//import scala.io.Source

object Main extends App {

  def f(x: List[Int], y: List[Int] = List.empty)(g: Int => Int): List[Int] =
    x match {
      case Nil => y
      case h :: t => f(t, y ++ List(g(h)))(g)
    }

  println(
    f(List(1, 2, 3))(x => x + 1)
  )

  //  val parse = parser.base.Base.parse(
  //    parser.expression.Arithmetic.apply,
  //    {
  //      val io = Source.fromFile(Path.of("demo/main.zapl").toUri)
  //      val source = io.mkString
  //      io.close()
  //      source
  //    }
  //  )
  //
  //  parse match {
  //    case parser.base.Base.Success(s, _) =>
  //      println(s)
  //      pprint.pprintln(s, height = 9000, width = 2)
  //
  //    case parser.base.Base.NoSuccess(s, _) =>
  //      throw new RuntimeException(s)
  //  }

  //
  //  object CustomParser {
  //
  //    trait Parser[T]
  //
  //    trait Result
  //
  //    case class Success(result: String, rest: String) extends Result
  //
  //    case class Failure(msg: String) extends Result
  //
  //    case class DefaultParser(input: String) {
  //
  //      def lowerAlpha: Parser[String] = """[a-z]""".r
  //
  //      def upperAlpha: Parser[String] = """[A-Z]""".r
  //
  //      def alpha: Parser[String] = or(lowerAlpha, upperAlpha)
  //
  //      def or(a: Any, b: Any): Parser[Any] = {
  //
  //      }
  //    }
  //
  //  }
  //
  //  CustomParser.DefaultParser("a")

}