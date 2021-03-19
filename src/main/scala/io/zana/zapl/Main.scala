package io.zana.zapl

import io.zana.zapl.standard.{Compiler, Error}

object Main extends App {
    Compiler(args, display = true)
}
