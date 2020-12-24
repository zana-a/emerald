package io.zana.zapl.structure

import io.zana.zapl.structure.Expression.Expression
import io.zana.zapl.structure.Statement.Statement

object Base {

  sealed trait Base

  case class Program(statementsOrExpressions: List[Statement | Expression])
    extends Base

}