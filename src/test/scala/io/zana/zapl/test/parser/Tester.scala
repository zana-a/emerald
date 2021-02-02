package io.zana.zapl.test.parser

import io.zana.zapl.parser
import org.junit.Assert.assertEquals

class Tester {

  object Default {

    import parser.Base._

    def tester(parser: Parser[Any], input: String, expected: Any): Unit = {
      parse(parser, input) match {
        case Success(s, _) => assertEquals(expected, s)
        case Failure(s, _) => assert(assertion = false, s)
        case Error(s, _) => assert(assertion = false, s)
      }
    }
  }

}
