package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics.Point

/**
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
class Cube(val x:Int, val y:Int, val z:Int) {

  def toPoint:Point = {
    val pointX = Hexagon.radius * 1.5f * x
    val pointY = Hexagon.radius * Hexagon.Sqrt3 * (z + x/2.0f)
    new Point(pointX.toFloat, pointY.toFloat)
  }

  override def toString = "(" + x + ", " + y + ", " + z + ")"

}
