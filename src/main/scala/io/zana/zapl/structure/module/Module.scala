package io.zana.zapl.structure.module

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.common.Identifier

// TODO: Change Any to Specific Type: Restrict to function and mods
case class Module(name: Identifier, body: List[Any]) extends Structure
