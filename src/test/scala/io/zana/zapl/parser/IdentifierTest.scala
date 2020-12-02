package io.zana.zapl.parser

import io.zana.zapl.parser.Parser._
import org.junit.Assert._
import org.junit._

class IdentifierTest {

  @Test
  def `alpha` = {
    val result = parse(identifier, "a")
    result match {
      case Success(result, _) => assert(true, result)
      case Failure(msg, _) => assert(false, msg)
      case Error(msg, _) => assert(false, msg)
    }
  }

  @Test
  def `alpha followed by numeric` = {
    val result = parse(identifier, "a1")
    result match {
      case Success(result, _) => assert(true, result)
      case Failure(msg, _) => assert(false, msg)
      case Error(msg, _) => assert(false, msg)
    }
  }

  @Test()
  def `numeric followed by alpha` = {
    val result = parse(identifier, "1a")
    result match {
      case Success(result, _) => assert(false, result)
      case Failure(msg, _) => assert(true, msg)
      case Error(msg, _) => assert(false, msg)
    }
  }

  @Test
  def `many mixed alphanumeric` = {
    val result = parse(
      identifier,
      "a1b2c3d4e5f6g7h8i9j1k2l3m4n5o6p7q8r9s0t1u2v3w4x5y6z7"
    )
    result match {
      case Success(result, _) => assert(true, result)
      case Failure(msg, _) => assert(false, msg)
      case Error(msg, _) => assert(false, msg)
    }
  }
}
