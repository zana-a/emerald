package io.zana.zapl.structure.common

object StaticTypes {

  trait Static

  case object String extends Static

  case object Int extends Static

  case object Boolean extends Static

  //todo : perhaps move the tostring override to a structure translator
  case class List(generic: Static) extends Static {
    override def toString: String = s"List[$generic]"
  }

}
