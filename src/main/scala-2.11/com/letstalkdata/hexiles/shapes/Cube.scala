package com.letstalkdata.hexiles
package shapes

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
case class Cube(x: Long, y: Long, z: Long) {

  def toPoint: Point[Double] = {
    val pointX = Hexagon.radius * 1.5f * x
    val pointY = Hexagon.radius * Hexagon.Sqrt3 * (z + x / 2.0f)
    Point(pointX, pointY)
  }

  override def toString = "(" + x + ", " + y + ", " + z + ")"

}
