package com.letstalkdata.hexiles.graphics

/**
 * Author: Phillip Johnson
 * Date: 5/3/15
 */
object Colors {
  sealed abstract class Color(html:String) {
    override def toString = html
  }
  case object White extends Color("#FFFFFF")
  case object Black extends Color("#000000")
  case object Olivine extends Color("#9AB973")
  case object Tan extends Color("#CBA87E")
  case object Mauve extends Color("#B06D87")
  case object Periwinkle extends Color("#527181")
  case object Gold extends Color("#CBC27E")
  case object Purple extends Color("#6C5B8B")
  case object Gray20 extends Color("#333333")
  case object Gray60 extends Color("#999999")
}
