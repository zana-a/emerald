package io.zana.zapl.translator.control

import io.zana.zapl.structure.block.Block
import io.zana.zapl.structure.call.Callable
import io.zana.zapl.structure.control.{Arm, Control}
import io.zana.zapl.structure.expression.Expression
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.primitive.Primitive
import io.zana.zapl.translator.Translatable
import io.zana.zapl.{translator, structure => structures}

import scala.collection.mutable.ListBuffer

object Cond extends Translatable[structures.control.Cond] {


  private def generate(n: List[Arm]): List[(String, String)] = {
    for {arm <- n} yield {
      val guard = translator.expression.Expression(arm.guard)
      val command = arm.command match {
        case e: Identifier => translator.identifier.Identifier(e)
        case e: Callable => translator.call.Callable(e)
        case e: Expression => translator.expression.Expression.sanitise(
          translator.expression.Expression(e)
        )
        case e: Control => translator.control.Control(e)
        case e: Primitive => translator.primitive.Primitive(e)
        case e: Block => translator.block.Block(e)
        case e => throw new Error(s"did not know how to translate $e")
      }
      (guard, command)
    }
  }

  override def apply(structure: structures.control.Cond): String = {
    val allArms = structure.arms match {
      case Some(value) => generate(value)
      case None => List()
    }

    val defaultArm = structure.default match {
      case Some(value) => generate(List(value))
      case None => List()
    }

    def build: String = {

      def exprSanitise(s: String): String =
        translator.expression.Expression.sanitise(s)

      var result = new ListBuffer[String]()

      defaultArm match {
        case dh :: _ => allArms match {
          case ah :: at => {
            result += (s"if (${exprSanitise(ah._1)}) ${ah._2}")
            for {it <- at} yield result += s"else if (${exprSanitise(it._1)}) ${it._2}"
            result += s"else ${dh._2}"
          }
          case Nil => result += s"if (${exprSanitise(dh._1)}) ${dh._2}"
        }
        case Nil => allArms match {
          case ah :: at => {
            result += s"if (${exprSanitise(ah._1)}) ${ah._2}"
            for {it <- at} yield result += s"else if (${exprSanitise(it._1)}) ${it._2}"
          }
          case Nil => Nil
        }
      }
      result.mkString("\n")
    }

    build
  }

}
