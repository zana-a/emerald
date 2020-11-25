package io.zana.zapl.ast

import io.zana.zapl.ast.Type._

object Expression {

  trait Expressor

  object Call {

    trait Callable extends Expressor {
      val identifier: java.lang.String

      override def toString: java.lang.String =
        s"Callable.${getClass.getSimpleName}($identifier)"
    }

    case class Definition(override val identifier: java.lang.String) extends Callable

    case class Function(override val identifier: java.lang.String, params: scala.List[Type])
      extends Callable

    case class Module(override val identifier: java.lang.String) extends Callable

  }

  object Operator {

    protected trait Operator

    trait Math extends Operator

    trait Logic extends Operator

    case object + extends Math with Math.Accepted

    case object - extends Math with Math.Accepted

    case object * extends Math with Math.Accepted

    case object / extends Math with Math.Accepted

    case object || extends Logic with Logic.Accepted

    case object && extends Logic with Logic.Accepted

    case object < extends Logic with Logic.Accepted

    case object > extends Logic with Logic.Accepted

    case object <= extends Logic with Logic.Accepted

    case object >= extends Logic with Logic.Accepted

    case object == extends Logic with Logic.Accepted

    case object != extends Logic with Logic.Accepted

  }

  // accepts the following: math.bracket, math operators and integers
  object Math {

    trait Accepted

    case class Bracket(expression: scala.List[Accepted]) extends Accepted

    case class Expression(expression: scala.List[Accepted]) extends Expressor

  }

  // accepts the following: logic.bracket, logic operators and booleans
  object Logic {

    trait Accepted

    case class Bracket(expression: scala.List[Accepted]) extends Accepted

    case class Expression(expression: scala.List[Accepted]) extends Expressor

  }

  object String {

    case class Expression(strings: scala.List[String]) extends Expressor

  }

  case class Definition(identifier: String, value: Type) extends Expressor

}