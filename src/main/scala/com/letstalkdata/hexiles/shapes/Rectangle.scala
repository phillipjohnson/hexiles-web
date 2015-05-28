package com.letstalkdata.hexiles.shapes

import com.letstalkdata.hexiles.graphics.Point

/**
 * Author: Phillip Johnson
 * Date: 5/16/15
 */
case class Rectangle(var topLeft:Point, var bottomRight:Point) {
  def topRight = new Point(bottomRight.x, topLeft.y)
  def bottomLeft = new Point(topLeft.x, bottomRight.y)

  def intersects(that:Rectangle):Boolean = {
    this.topLeft.x < that.bottomRight.x &&
      this.bottomRight.x > that.topLeft.x &&
      this.topLeft.y < that.bottomRight.y &&
      this.bottomRight.y > that.topLeft.y
  }
}
