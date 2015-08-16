package com.letstalkdata.hexiles
package shapes

import com.letstalkdata.hexiles.graphics.Point

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
case class Cube(x: Int, y: Int, z: Int) {

  def toPoint: Point = {
    val pointX = Hexagon.radius * 1.5f * x
    val pointY = Hexagon.radius * Hexagon.Sqrt3 * (z + x / 2.0f)
    Point(pointX.toFloat, pointY.toFloat)
  }

  override def toString = "(" + x + ", " + y + ", " + z + ")"

}
