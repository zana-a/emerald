package io.zana.zapl.structure.statics

object Static {

  trait Static

  case object String extends Static

  case object Int extends Static

  case object Boolean extends Static

  case class List(generic: Static) extends Static

  case object Any extends Static

  case object Unit extends Static

}
