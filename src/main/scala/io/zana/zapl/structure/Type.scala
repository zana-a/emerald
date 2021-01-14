package io.zana.zapl.structure

object Type {

	sealed abstract class Type[T](val value: T)

	case class String(override val value: java.lang.String) extends Type(value)

	case class Boolean(override val value: scala.Boolean) extends Type(value)

	case class Integer(override val value: scala.Int) extends Type(value)

	case class List(override val value: scala.List[Any])
		extends Type(value) {

		override def toString: Predef.String = {
			value.toString
		}
	}

}
