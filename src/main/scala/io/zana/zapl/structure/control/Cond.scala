package io.zana.zapl.structure.control

case class Cond(arms: Option[List[Arm]], default: Option[Arm]) extends Control
