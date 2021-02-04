package io.zana.zapl

object Main extends App {

  import structure.primitive.{Integer => IntegerStructure}
  import translator.primitive.{Integer => IntegerTranslator}

  IntegerTranslator.translate(
    IntegerStructure(1)
  )
}
