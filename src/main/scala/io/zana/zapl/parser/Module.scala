package io.zana.zapl
package parser

trait Module extends Base {
  def module_identifier: Parser[String] =
    """^([A-Z])(\w?)+(\d?)+""".r

  def module: Parser[Any] =
    MOD ~ module_identifier ~ DO ~ opt(rep(function)) ~ END
}
