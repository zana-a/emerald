package io.zana.zapl.ast

import io.zana.zapl.ast.Expression.Math.Accepted
import io.zana.zapl.ast.Type._

object Expression {

  trait Expression

  case class Definition(identifier: String, ue: Type) extends Expression

  object Call {

    trait Callable extends Expression {
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

    trait Math extends Operator

    trait Logic extends Operator

    protected trait Operator

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

    case class Express(expression: scala.List[Accepted]) extends Expression

  }

  // accepts the following: logic.bracket, logic operators and booleans
  object Logic {

    trait Accepted

    case class Bracket(expression: scala.List[Accepted]) extends Accepted

    case class Express(expression: scala.List[Accepted]) extends Expression

  }

  object String {

    case class Express(strings: scala.List[String]) extends Expression

  }

}