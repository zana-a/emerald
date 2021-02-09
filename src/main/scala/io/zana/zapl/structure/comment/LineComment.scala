package io.zana.zapl.structure.comment

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.module.ModuleBody

case class LineComment(comment: String) extends Structure with ModuleBody
