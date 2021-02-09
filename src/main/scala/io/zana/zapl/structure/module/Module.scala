package io.zana.zapl.structure.module

import io.zana.zapl.Structure
import io.zana.zapl.structure.common.Identifier

case class Module(name: Identifier, body: List[ModuleBody]) extends Structure
  with ModuleBody
