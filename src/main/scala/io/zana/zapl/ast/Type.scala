package io.zana.zapl.ast

import io.zana.zapl.ast.Expression._

object Type {

  trait Type

  case class Integer(value: Int) extends Type with Math.Accepted

  case class Bool(value: Boolean) extends Type with Logic.Accepted

  case class String(value: java.lang.String) extends Type

  case class List(values: scala.List[Type]) extends Type

}
