package io.zana.zapl
package structure

object Auxiliary {

  sealed trait Auxiliary

  case class Identifier(value: String) extends Auxiliary

}

