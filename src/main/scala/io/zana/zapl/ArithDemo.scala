//package io.zana.zapl
//
//import scala.util.parsing.combinator._
//
//object ArithDemo extends App {
//
//
//  object Parser extends JavaTokenParsers {
//
//    case class Lit(i: Int)
//
//    case class Var(str: String)
//
//    trait Expr
//
//    case class One(a: String, b: Int => Int, c: Any) extends Expr
//
//    case class Two(a: String, b: (Int, Int) => Int, c: Any, d: Any) extends Expr
//
//
//    def number: Parser[Lit] = wholeNumber ^^ (n => Lit(n.toInt))
//
//    def variable: Parser[Var] = ident ^^ (i => Var(i))
//
//    def toInt(b: Boolean): Int = {
//      if (b) 1
//      else 0
//    }
//
//    def toBool(i: Int): Boolean = {
//      if (i == 1) true
//      else false
//    }
//
//    def relop: Parser[(Expr, Expr) => Expr] = {
//      ("==" | "!=" | "<=" | "<" | ">=" | ">" | "<=") ^^ {
//        case "==" => (e1, e2) => Two("==", (x, y) => toInt(x == y), e1, e2)
//        case "!=" => (e1, e2) => Two("!=", (x, y) => toInt(x != y), e1, e2)
//        case "<=" => (e1, e2) => Two("<=", (x, y) => toInt(x <= y), e1, e2)
//        case "<" => (e1, e2) => Two("<", (x, y) => toInt(x < y), e1, e2)
//        case ">=" => (e1, e2) => Two(">=", (x, y) => toInt(x >= y), e1, e2)
//        case ">" => (e1, e2) => Two(">", (x, y) => toInt(x > y), e1, e2)
//      }
//    }
//
//
//    def addop: Parser[(Expr, Expr) => Expr] = {
//      ("+" | "-") ^^ {
//        case "+" => (e1, e2) => Two("+", _ + _, e1, e2)
//        case "-" => (e1, e2) => Two("-", _ - _, e1, e2)
//      }
//    }
//
//
//    def mulop: Parser[(Expr, Expr) => Expr] = {
//      ("*" | "/" | "%") ^^ {
//        case "*" => (e1, e2) => Two("*", _ * _, e1, e2)
//        case "/" => (e1, e2) => Two("/", _ / _, e1, e2)
//        case "%" => (e1, e2) => Two("%", _ % _, e1, e2)
//      }
//    }
//
//    def negop: Parser[Expr => Expr] = "-" ^^ (_ => e => One("-", x => -x, e))
//
//
//    def andop: Parser[(Expr, Expr) => Expr] = "&&" ^^ (_ =>
//
//      (e1, e2) => Two("&&", (x, y) => toInt(toBool(x) && toBool(y)), e1, e2))
//
//
//    def orop: Parser[(Expr, Expr) => Expr] = "||" ^^ (_ =>
//      (e1, e2) => Two("||", (x, y) => toInt(toBool(x) || toBool(y)), e1, e2))
//
//
//    def impop: Parser[(Expr, Expr) => Expr] = "implies" ^^ (_ =>
//      (e1, e2) => Two("implies", (x, y) => toInt(!toBool(x) || toBool(y)), e1, e2))
//
//
//    def eqvop: Parser[(Expr, Expr) => Expr] = "equiv" ^^ (_ =>
//      (e1, e2) => Two("equiv", (x, y) => toInt(toBool(x) == toBool(y)), e1, e2))
//
//
//    def notop: Parser[Expr => Expr] = {
//      "~" ^^ (_ => e => One("~", x => if (x == 0) 1 else 0, e))
//    }
//
//    def expr: Parser[Expr] = chainl1(equivTerm, equivTerm, eqvop)
//
//    def equivTerm: Parser[Expr] = chainl1(impTerm, impTerm, impop)
//
//    def impTerm: Parser[Expr] = chainl1(orTerm, orTerm, orop)
//
//    def orTerm: Parser[Expr] = chainl1(andTerm, andTerm, andop)
//
//    def andTerm: Parser[Expr] = chainl1(relTerm, relTerm, relop)
//
//    def relTerm: Parser[Expr] = chainl1(addTerm, addTerm, addop)
//
//    def addTerm: Parser[Expr] = chainl1(factor, factor, mulop)
//
//    def factor: Parser[Expr] =
//      (negop | notop) ~ factor ^^ { case f ~ e => f(e.asInstanceOf[Expr]) } |
//        number | variable | "(" ~> expr <~ ")"
//  }
//
//
//  println(Parser.parseAll(Parser.expr, "1 + 2"))
//
//}
