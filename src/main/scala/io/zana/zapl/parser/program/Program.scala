package io.zana.zapl.parser.program

import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.control.Control._
import io.zana.zapl.parser.function.Function._
import io.zana.zapl.parser.module.Module._
import io.zana.zapl.parser.variable.Variable._
import io.zana.zapl.structure.program.{Program => Structure}

object Program {

  def program: Parser[Structure] = rep(
    lineComment
      | module
      | call
      | function
      | variable
      | control
  ) ^^ (statements => Structure(statements))
}
