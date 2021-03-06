package com.letstalkdata.hexiles.graphics

/**
 * A pixel on the screen.
 *
 * Author: Phillip Johnson
 * Date: 4/27/15
 */
case class Point(x:Float, y:Float) {

  override def toString = "(" + x + ", " + y + ")"
}
