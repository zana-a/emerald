package io.zana.zapl.ast

import io.zana.zapl.ast.Expression._

object Type {

  trait Type

  case class Integer(i: Int) extends Type with Math.Accepted

  case class Bool(b: Boolean) extends Type with Logic.Accepted

  case class String(s: java.lang.String) extends Type

  case class List(l: scala.List[Type]) extends Type

}
