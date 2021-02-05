package io.zana.zapl.structure.module

import io.zana.zapl.Structure
import io.zana.zapl.structure.common.Identifier
import io.zana.zapl.translator.module.ModuleBody

case class Module(name: Identifier, body: List[ModuleBody]) extends Structure
  with ModuleBody
