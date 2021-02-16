package io.zana.zapl.structure.control

case class Loop(arms: Option[List[Arm]], default: Option[Arm]) extends Control
