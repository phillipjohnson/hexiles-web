package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.{Colors, Point, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom.CanvasRenderingContext2D

/**
 * Author: Phillip Johnson

 */
class Piece(color:Colors.Color) extends Drawable {
  var x = 0
  var y = 0

  val hexes = List(new Hexagon(3,3))

  override def draw(context: CanvasRenderingContext2D): Unit = {
    hexes.foreach(hex => hex.draw(context, color))
  }

  override def contains(point: Point): Boolean = hexes.exists(hex => hex.contains(point))
}
