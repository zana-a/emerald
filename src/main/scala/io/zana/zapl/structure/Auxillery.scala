package io.zana.zapl.structure


object Auxillery {

  sealed trait Auxillery

  case class Identifier(value: String) extends Auxillery

}
