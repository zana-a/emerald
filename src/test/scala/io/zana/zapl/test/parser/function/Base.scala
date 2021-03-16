package io.zana.zapl.test.parser.function

trait Base {

  object Tools {

    object Function {
      def parse =
        io.zana.zapl.parser.function.Function.apply

      def structure =
        io.zana.zapl.structure.function.Function

    }

    object Parameter {
      def parse =
        io.zana.zapl.parser.function.Parameter.apply

      def structure =
        io.zana.zapl.structure.function.Parameter

    }

  }

}
