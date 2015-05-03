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
}
