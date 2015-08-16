package com.letstalkdata.hexiles
package graphics

/**
 * Author: Phillip Johnson
 * Date: 5/3/15
 */
object Colors {

  sealed abstract class Color(html: String) {
    override def toString = html
  }

  case object White extends Color("#FFFFFF")
  case object Black extends Color("#000000")
  case object Olivine extends Color("#D3BA47")
  case object Tan extends Color("#CBA87E")
  case object Pink extends Color("#BB3F67")
  case object Periwinkle extends Color("#527181")
  case object Melon extends Color("#D39047")
  case object Gold extends Color("#ACCA44")
  case object Forest extends Color("#329560")
  case object Cerulean extends Color("#3A518E")
  case object Purple extends Color("#63378E")
  case object Gray20 extends Color("#333333")
  case object Gray60 extends Color("#999999")

}
