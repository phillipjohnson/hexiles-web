package com.letstalkdata.hexiles.game

import com.letstalkdata.hexiles.graphics.{Colors, Point, Drawable}
import com.letstalkdata.hexiles.shapes.Hexagon
import org.scalajs.dom.CanvasRenderingContext2D

/**
 * Author: Phillip Johnson

 */
class Piece(hexes:Seq[Hexagon], color:Colors.Color) extends Drawable {

  def x = hexes(0).x
  def y = hexes(0).y

  def move(dx:Float, dy:Float): Unit = {
    for(hex <- hexes) {
      hex.x += dx
      hex.y += dy
    }
  }

  def snapToGrid() = {
    for(hex <- hexes) {
      hex.snapToGrid(hex.centerPoint())
    }
  }

  def resetGridPosition(point:Point) = {
    for(hex <- hexes) {
      hex.resetGridPosition(point)
    }
  }

  def rotateLeft() = hexes.foreach(_.rotateLeft(hexes(0)))

  def rotateRight() = hexes.foreach(_.rotateRight(hexes(0)))

  def flip() = hexes.foreach(_.flipOver(hexes(0)))

  override def draw(context: CanvasRenderingContext2D): Unit = {
    hexes.foreach(hex => hex.draw(context, color))
  }

  override def contains(point: Point): Boolean = hexes.exists(hex => hex.contains(point))
}
